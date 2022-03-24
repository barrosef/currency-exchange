package challenge.currencyexchange.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateRequest {

    @NotNull
    private String convertFromSymbol;

    @NotNull
    private Double valueToConvert;

    @NotNull
    private String symbolToConvert;

    @JsonIgnore
    private String requestUserLogin;
}