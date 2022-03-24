package challenge.currencyexchange.service;

import challenge.currencyexchange.client.ExchangeRatesClient;
import challenge.currencyexchange.client.ExchangeRatesDTO;
import challenge.currencyexchange.domain.Rate;
import challenge.currencyexchange.domain.User;
import challenge.currencyexchange.domain.repository.UserRepository;
import challenge.currencyexchange.resources.ExchangeRateRequest;
import challenge.currencyexchange.resources.ExchangeRateResponse;
import challenge.currencyexchange.util.ExchangeRateConfig;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;

import java.time.LocalDateTime;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@RequestScoped
public class ExchangeRateBuilder {

    ExchangeRatesDTO dto;
    Rate rate;
    User userRating;

    public ExchangeRateBuilder validate(User rootUser, ExchangeRateRequest request, ExchangeRateConfig config,
                                        UserRepository userRepository) {
        if (rootUser.getLogin().equals(request.getRequestUserLogin())){
            new WebApplicationException("Only non root users can convert coins", UNAUTHORIZED);
        }
        if (!config.permittedCoins().contains(request.getBase())) {
            new WebApplicationException("Only non root users can convert coins", UNAUTHORIZED);
        }
        this.userRating = userRepository.findByLogin(request.getRequestUserLogin()).orElseThrow(() ->
                new WebApplicationException("User can't convert or not found", UNAUTHORIZED));
        return this;
    }

    public ExchangeRateBuilder exchange(ExchangeRateRequest request, ExchangeRatesClient client, ExchangeRateConfig config) {
        this.dto = client.getRatesExchangeRates(config.apiAccessKey(), request.getBase(), request.getSymbols());
        return this;
    }

    public ExchangeRateBuilder createRate(ExchangeRateRequest request, ExchangeRateService service) {
        this.rate = Rate.builder()
                .dateTime(LocalDateTime.now())
                .build();
        //TODO build this.rate
        return this;
    }

    public ExchangeRateResponse build() {
        //TODO build ExchangeRateResponse and reurnt it
        return null;
    }
}
