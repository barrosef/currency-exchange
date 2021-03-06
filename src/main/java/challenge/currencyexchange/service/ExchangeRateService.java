package challenge.currencyexchange.service;

import challenge.currencyexchange.client.ExchangeRatesClient;
import challenge.currencyexchange.domain.User;
import challenge.currencyexchange.domain.repository.RateRepository;
import challenge.currencyexchange.domain.repository.UserRepository;
import challenge.currencyexchange.resources.ExchangeRateListResponse;
import challenge.currencyexchange.resources.ExchangeRateRequest;
import challenge.currencyexchange.resources.ExchangeRateResponse;
import challenge.currencyexchange.util.ExchangeRateConfig;
import io.quarkus.runtime.Startup;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Startup
@ApplicationScoped
public class ExchangeRateService {

    private UserRepository userRepository;

    private RateRepository rateRepository;

    private ExchangeRateConfig config;

    private ExchangeRateBuilder rateBuilder;
    private ListRatesBuilder listBuilder;

    @RestClient
    private ExchangeRatesClient exchangeRatesClient;

    private User rootUser;

    @Inject
    public ExchangeRateService(UserRepository userRepository, RateRepository rateRepository, ExchangeRateConfig config,
                               ExchangeRateBuilder builder, ListRatesBuilder listBuilder) {
        this.userRepository = userRepository;
        this.rateRepository = rateRepository;
        this.config = config;
        this.rateBuilder = builder;
        this.listBuilder = listBuilder;
        this.init();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ExchangeRateResponse rate(ExchangeRateRequest exchangeRateRequest) {
        return rateBuilder.validate(this.rootUser, exchangeRateRequest, this.config, this.userRepository)
                .exchange(this.exchangeRatesClient)
                .convert()
                .createRate(this.rateRepository)
                .build();
    }

    public List<ExchangeRateListResponse> list(String userLogin) {
        return  listBuilder
                    .validate(rootUser, userLogin, this.userRepository)
                    .listRates(rateRepository)
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
