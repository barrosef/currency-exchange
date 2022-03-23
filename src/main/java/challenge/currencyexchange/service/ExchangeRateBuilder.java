package challenge.currencyexchange.service;

import challenge.currencyexchange.client.ExchangeRatesClient;
import challenge.currencyexchange.client.ExchangeRatesDTO;
import challenge.currencyexchange.domain.Rate;
import challenge.currencyexchange.domain.User;
import challenge.currencyexchange.resources.ExchangeRateRequest;
import challenge.currencyexchange.resources.ExchangeRateResponse;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ExchangeRateBuilder {

    ExchangeRatesDTO dto;
    Rate rate;
    User userRating;

    public ExchangeRateBuilder validate(User rootUser, ExchangeRateRequest request) {
        //TODO validate user non_root_user and try exception
        //TODO validate the base currency is EUR and try exception
        return this;
    }

    public ExchangeRateBuilder exchange(ExchangeRateRequest request, ExchangeRatesClient client) {
        //TODO exchange with api and set dto
        return this;
    }

    public ExchangeRateBuilder createRate(ExchangeRateService service) {
        //TODO load user
        //TODO build this.rate
        return this;
    }

    public ExchangeRateResponse build() {
        //TODO build ExchangeRateResponse and reurnt it
        return null;
    }
}
