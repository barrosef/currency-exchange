package currencyexchange.service;

import challenge.currencyexchange.client.ExchangeRatesClient;
import challenge.currencyexchange.domain.User;
import challenge.currencyexchange.domain.repository.RateRepository;
import challenge.currencyexchange.domain.repository.UserRepository;
import challenge.currencyexchange.resources.ExchangeRateRequest;
import challenge.currencyexchange.service.ExchangeRateBuilder;
import challenge.currencyexchange.util.ExchangeRateConfig;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

@QuarkusTest
public class ExchangeRateBuilderTest {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RateRepository rateRepository;

    @Inject
    private ExchangeRateConfig config;

    @Inject
    private ExchangeRateBuilder rateBuilder;

    private User rootUser;

    @RestClient
    private ExchangeRatesClient exchangeRatesClient;

    @Test
    @Transactional
    public void testRateWithRootUser() {
        this.rootUser = this.userRepository.findByLogin(config.rootUserLogin())
                .orElseThrow(() ->
                        new RuntimeException("Could not instantiate application, no root user configuration found"));

        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest("EUR", 100d, "USD", "root");
        Assertions.assertThrows(WebApplicationException.class, () -> rateBuilder.validate(this.rootUser, exchangeRateRequest, this.config, this.userRepository)
                .exchange(this.exchangeRatesClient)
                .convert()
                .createRate(this.rateRepository)
                .build());
    }

    @Test
    @Transactional
    public void testRateWithNonRootUser() {
        this.rootUser = this.userRepository.findByLogin(config.rootUserLogin())
                .orElseThrow(() ->
                        new RuntimeException("Could not instantiate application, no root user configuration found"));

        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest("EUR", 100d, "USD", "rate");
        Assertions.assertTrue(rateBuilder.validate(this.rootUser, exchangeRateRequest, this.config, this.userRepository)
                .exchange(this.exchangeRatesClient)
                .convert()
                .createRate(this.rateRepository)
                .build().getConvertedValue() > 0);
    }
}