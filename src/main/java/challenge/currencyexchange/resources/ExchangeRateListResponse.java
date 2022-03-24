package challenge.currencyexchange.resources;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRateListResponse {
    private Long rateId;
    private String convertFromSymbol;
    private Double valueToConvert;
    private String symbolToConvert;
    private Double conversionTax;
    private LocalDateTime dateTime;
}
