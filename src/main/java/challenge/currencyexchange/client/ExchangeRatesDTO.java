package challenge.currencyexchange.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRatesDTO {

    Boolean success;
    Long timestamp;
    String base;
    String date;
    Map<String, Double> rates;
}
