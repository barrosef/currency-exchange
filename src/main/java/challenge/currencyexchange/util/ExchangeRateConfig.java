package challenge.currencyexchange.util;

import io.smallrye.config.ConfigMapping;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@ConfigMapping(prefix = "challenge.currencyexchange")
public interface ExchangeRateConfig {

    String apiAccessKey();
    String rootUserLogin();
    List<String> permittedCoins();

}
