package challenge.currencyexchange.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_cextb00")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;
}
