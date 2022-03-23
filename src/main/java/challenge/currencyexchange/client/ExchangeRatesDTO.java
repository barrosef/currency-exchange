package challenge.currencyexchange.client;

import java.util.Map;

public class ExchangeRatesDTO {

    Boolean success;
    Long timestamp;
    String base;
    String date;
    Map<String, Double> rates;
}
