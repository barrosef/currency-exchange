package challenge.currencyexchange.service;

import challenge.currencyexchange.domain.User;
import challenge.currencyexchange.resources.ExchangeRateResponse;

import java.util.ArrayList;
import java.util.List;

public class ListRatesBuilder {
    
    private boolean isRootUser;
    private String login;

    public ListRatesBuilder validate(User rooUser, String login) {
        //TODO validate
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
