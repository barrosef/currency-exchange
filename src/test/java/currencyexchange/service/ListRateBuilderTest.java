package currencyexchange.service;

import challenge.currencyexchange.domain.User;
import challenge.currencyexchange.domain.repository.RateRepository;
import challenge.currencyexchange.domain.repository.UserRepository;
import challenge.currencyexchange.service.ListRatesBuilder;
import challenge.currencyexchange.util.ExchangeRateConfig;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

@QuarkusTest
public class ListRateBuilderTest {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RateRepository rateRepository;

    @Inject
    private ListRatesBuilder listBuilder;

    @Inject
    private ExchangeRateConfig config;

    @Test
    public void listRateTest() {
        User rootUser = this.userRepository.findByLogin(config.rootUserLogin())
                .orElseThrow(() ->
                        new RuntimeException("Could not instantiate application, no root user configuration found"));

        List list = listBuilder
                .validate(rootUser, "root", this.userRepository)
                .listRates(rateRepository)
                .build();
        Assertions.assertFalse(list.isEmpty());
    }
}
