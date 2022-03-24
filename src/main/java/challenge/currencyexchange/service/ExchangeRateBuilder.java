package challenge.currencyexchange.service;

import challenge.currencyexchange.client.ExchangeRatesClient;
import challenge.currencyexchange.client.ExchangeRatesDTO;
import challenge.currencyexchange.domain.Rate;
import challenge.currencyexchange.domain.User;
import challenge.currencyexchange.domain.repository.RateRepository;
import challenge.currencyexchange.domain.repository.UserRepository;
import challenge.currencyexchange.resources.ExchangeRateRequest;
import challenge.currencyexchange.resources.ExchangeRateResponse;
import challenge.currencyexchange.util.ExchangeRateConfig;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.Arrays;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@RequestScoped
public class ExchangeRateBuilder {

    private ExchangeRateRequest request;
    private ExchangeRateConfig config;
    private ExchangeRatesDTO dto;
    private Double convertedValue;
    private Double conversionTax;
    private Rate rate;
    private User userRating;

    public ExchangeRateBuilder validate(User rootUser, ExchangeRateRequest request, ExchangeRateConfig config,
                                        UserRepository userRepository) {
        if (rootUser.getLogin().equals(request.getRequestUserLogin())){
            throw new WebApplicationException("Only non root users can convert coins", UNAUTHORIZED);
        }
        if (!config.permittedCoins().contains(request.getConvertFromSymbol())) {
            throw new WebApplicationException("No authorized convert convertFromSymbol delivered", UNAUTHORIZED);
        }
        this.userRating = userRepository.findByLogin(request.getRequestUserLogin()).orElseThrow(() ->
            new WebApplicationException("User can't convert or not found", UNAUTHORIZED));
        this.request = request;
        this.config = config;
        return this;
    }

    public ExchangeRateBuilder exchange(ExchangeRatesClient client) {
        this.dto = client.getRatesExchangeRates(config.apiAccessKey(), request.getConvertFromSymbol(),
                Arrays.asList(request.getSymbolToConvert()));
        return this;
    }

    public ExchangeRateBuilder convert() {
        this.convertedValue = dto.getRates().get(request.getSymbolToConvert()) * request.getValueToConvert();
        this.conversionTax = this.convertedValue * config.conversionTax() / 100;
        return this;
    }

    public ExchangeRateBuilder createRate(RateRepository repository) {
        this.rate = Rate.builder()
                .user(this.userRating)
                .base(request.getSymbolToConvert())
                .baseValue(request.getValueToConvert())
                .symbolToConvert(request.getSymbolToConvert())
                .conversionTax(this.conversionTax)
                .dateTime(LocalDateTime.now())
                .build();
        repository.persist(rate);
        return this;
    }

    public ExchangeRateResponse build() {
        return ExchangeRateResponse.builder()
                .rateId(this.rate.getRateId())
                .userId(this.userRating.getUserId())
                .convertFromSymbol(this.rate.getBase())
                .valueToConvert(this.rate.getBaseValue())
                .symbolToConvert(this.rate.getSymbolToConvert())
                .convertedValue(this.convertedValue)
                .conversionTax(this.rate.getConversionTax())
                .dateTime(this.rate.getDateTime())
                .build();
    }
}
