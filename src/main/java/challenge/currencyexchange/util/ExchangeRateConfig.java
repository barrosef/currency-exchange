package challenge.currencyexchange.util;

import io.smallrye.config.ConfigMapping;

import java.util.List;

@ConfigMapping(prefix = "challenge.currencyexchange")
public interface ExchangeRateConfig {

    String apiAccessKey();
    String rootUserLogin();
    List<String> permittedCoins();
    Double conversionTax();

}
