package challenge.currencyexchange.service;

import challenge.currencyexchange.domain.Rate;
import challenge.currencyexchange.domain.User;
import challenge.currencyexchange.domain.repository.RateRepository;
import challenge.currencyexchange.domain.repository.UserRepository;
import challenge.currencyexchange.resources.ExchangeRateListResponse;
import challenge.currencyexchange.resources.ExchangeRateResponse;
import challenge.currencyexchange.util.ExchangeRateConfig;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@RequestScoped
public class ListRatesBuilder {

    @Inject
    ExchangeRateConfig config;

    private boolean isRootUser;
    private String login;
    private List<Rate> rates;
    private User user;

    public ListRatesBuilder validate(User rootUser, String login, UserRepository userRepository) {
        this.isRootUser = rootUser.getLogin().equals(login);
        this.user = userRepository.findByLogin(login).orElseThrow(() ->
                new WebApplicationException("User can't convert or not found", UNAUTHORIZED));
        return this;
    }

    public ListRatesBuilder listRates(RateRepository rateRepository) {
        if (isRootUser)
            this.rates = rateRepository.listAll();
        else {
            this.rates = rateRepository.findByUser(this.user);
        }
        return this;
    }

    public List<ExchangeRateListResponse> build() {
        this.rates
                .stream()
                .map(rate -> {
                    return ExchangeRateListResponse.builder()
                            .rateId(rate.getRateId())
                            .symbolToConvert(rate.getBase())
                            .valueToConvert(rate.getBaseValue())
                            .symbolToConvert(rate.getSymbolToConvert())
                            .conversionTax(rate.getConversionTax())
                            .dateTime(rate.getDateTime())
                            .build();
                })
                .collect(Collectors.toList());
        return null;
    }
}
