package challenge.currencyexchange.domain;

import lombok.*;

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
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "conversion_tax")
    @NotNull(message = "The conversion rate is required")
    private Double conversionTax;

    @Column
    @NotNull(message = "Date time reate is required")
    private LocalDateTime dateTime;

    @Column(name = "target")
    @NotNull(message = "The currency to convert is required")
    @Size(min = 3, max = 3)
    private String symbolToConvert;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user"))
    private User user;
}
