package challenge.currencyexchange.service;

import challenge.currencyexchange.client.ExchangeRatesClient;
import challenge.currencyexchange.domain.User;
import challenge.currencyexchange.domain.repository.RateRepository;
import challenge.currencyexchange.domain.repository.UserRepository;
import challenge.currencyexchange.resources.ExchangeRateRequest;
import challenge.currencyexchange.resources.ExchangeRateResponse;
import challenge.currencyexchange.util.ExchangeRateConfig;
import io.quarkus.runtime.Startup;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Startup
@ApplicationScoped
public class ExchangeRateService {

    private UserRepository userRepository;

    private RateRepository rateRepository;

    private ExchangeRateConfig config;

    private ExchangeRateBuilder builder;

    @RestClient
    private ExchangeRatesClient exchangeRatesClient;

    private User rootUser;

    @Inject
    public ExchangeRateService(UserRepository userRepository, RateRepository rateRepository, ExchangeRateConfig config,
                               ExchangeRateBuilder builder) {
        this.userRepository = userRepository;
        this.rateRepository = rateRepository;
        this.config = config;
        this.builder = builder;
        this.init();
    }

    public ExchangeRateResponse rate(ExchangeRateRequest exchangeRateRequest) {
        return builder.validate(rootUser, exchangeRateRequest)
                .exchange(exchangeRateRequest, exchangeRatesClient)
                .createRate(this)
                .build();
    }

    private void init() {
        this.loadRootUser();
    }

    private void loadRootUser() {
        this.rootUser = this.userRepository.findByLogin(config.rootUserLogin())
                .orElseThrow(() ->
                        new RuntimeException("Could not instantiate application, no root user configuration found"));
    }
}
