package challenge.currencyexchange.domain.repository;

import challenge.currencyexchange.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Optional<User> findByLogin(String login) {
        return find("#User.findByLogin", Parameters.with("login", login)).singleResultOptional();
    }
}
