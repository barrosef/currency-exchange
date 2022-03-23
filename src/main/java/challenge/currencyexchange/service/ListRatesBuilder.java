package challenge.currencyexchange.service;

import challenge.currencyexchange.domain.User;
import challenge.currencyexchange.resources.ExchangeRateResponse;
import challenge.currencyexchange.util.ExchangeRateConfig;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class ListRatesBuilder {

    @Inject
    ExchangeRateConfig config;

    private boolean isRootUser;
    private String login;

    public ListRatesBuilder validate(User rooUser, String login) {
        return this;
    }

    public ListRatesBuilder listRates() {
        //TODO isRootUser listAllRates if not list by user
        return this;
    }

    public List<ExchangeRateResponse> build() {
        //TODO load
        return new ArrayList<>();
    }
}
