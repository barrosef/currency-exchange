package challenge.currencyexchange.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name = "rate_cextb01")
@NamedQueries({
        @NamedQuery(name = "Rate.findByUser", query = "select r from Rate r where r.user = :user")
})
public class Rate {

    @Id
    @GeneratedValue
    @Column(name = "rate_id")
    private Long rateId;

    @Column(name = "base")
    @NotNull(message = "The currency base is required")
    @Size(min = 3, max = 3)
    private String base;

    @Column(name = "base_value")
    @NotNull(message = "The currency base value is required")
    private Double baseValue;

    @Column(name = "conversion_rate")
    @NotNull(message = "The conversion rate is required")
    private Double conversionRate;

    @Column
    @NotNull(message = "Date time reate is required")
    private LocalDateTime dateTime;

    @ElementCollection
    @CollectionTable(name = "rate_detail_cextb02",
            joinColumns = {@JoinColumn(name = "rate_detail_id", referencedColumnName = "rate_id")})
    @MapKeyColumn(name = "currency_name")
    @Column(name = "currency_value")
    private Map<String, Double> rateDetails = new LinkedHashMap<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user"))
    private User user;
}
