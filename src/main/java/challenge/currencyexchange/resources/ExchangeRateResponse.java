package challenge.currencyexchange.resources;

import java.time.LocalDateTime;
import java.util.Map;

public class ExchangeRateResponse {

    Long rateId;
    Long userId;
    String base;
    Double baseValue;
    Map<String, Double> target;
    Double conversionRate;
    LocalDateTime dateTime;
}
