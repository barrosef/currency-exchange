package challenge.currencyexchange.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateRequest {

    @NotNull
    private String base;

    @NotNull
    private List<String> symbols;

    @JsonIgnore
    private String requestUserLogin;
}
