package challenge.currencyexchange.resources;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ExchangeRateRequest {

    @NotNull
    private String base;

    @NotNull
    private List<String> symbols;
}
