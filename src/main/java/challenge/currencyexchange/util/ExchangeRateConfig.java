package challenge.currencyexchange.util;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "challenge.currencyexchange")
public interface ExchangeRateConfig {

    String apiAccessKey();
    String rootUserLogin();
}
