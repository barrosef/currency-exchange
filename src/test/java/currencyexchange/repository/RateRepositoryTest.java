package currencyexchange.repository;

import challenge.currencyexchange.domain.Rate;
import challenge.currencyexchange.domain.repository.RateRepository;
import challenge.currencyexchange.domain.repository.UserRepository;
import challenge.currencyexchange.util.ExchangeRateConfig;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@QuarkusTest
public class RateRepositoryTest {

    @Inject
    RateRepository repository;

    @Inject
    ExchangeRateConfig config;

    @Inject
    UserRepository userRepository;

    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void testRateRepository() {

        Map<String, Double> rateDetails = new LinkedHashMap<>();
        rateDetails.put("USD", 50d);
        Rate r = Rate.builder()
                .user(userRepository.findByLogin(config.rootUserLogin()).orElseThrow(() -> new WebApplicationException(
                        "Root User could not be found, check migrations configuration and sql files ")))
                .base("EUR")
                .baseValue(5d)
                .symbolToConvert("USD")
                .conversionTax(9d)
                .dateTime(LocalDateTime.now())
                .build();
        repository.persist(r);
        Assertions.assertTrue(! repository.findByUser(r.getUser()).isEmpty());
    }
}
