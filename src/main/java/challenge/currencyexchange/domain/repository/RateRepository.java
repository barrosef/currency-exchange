package challenge.currencyexchange.domain.repository;

import challenge.currencyexchange.domain.Rate;
import challenge.currencyexchange.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RateRepository implements PanacheRepository<Rate> {

    public List<Rate> findByUser(User user) {
        return find("Rate.findByUser", Parameters.with("user", user)).list();
    }
}
