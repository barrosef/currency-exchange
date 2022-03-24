package currencyexchange.repository;

import challenge.currencyexchange.domain.repository.UserRepository;
import challenge.currencyexchange.util.ExchangeRateConfig;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class UserRepositoryTest {

    @Inject
    UserRepository repository;

    @Inject
    ExchangeRateConfig config;

    @Test
    public void testeUserRepositoryMocking() throws Throwable {
        //Test if root user is pre loaded in application
        Assertions.assertTrue(repository.findByLogin(config.rootUserLogin()).isPresent());
    }
}
