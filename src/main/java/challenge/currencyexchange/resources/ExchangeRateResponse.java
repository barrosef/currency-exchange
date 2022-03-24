package challenge.currencyexchange.resources;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRateResponse {

    Long rateId;
    Long userId;
    String convertFromSymbol;
    Double valueToConvert;
    String symbolToConvert;
    Double convertedValue;
    Double conversionTax;
    LocalDateTime dateTime;
}