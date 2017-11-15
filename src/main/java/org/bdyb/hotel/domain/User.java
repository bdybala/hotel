package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Table(name = Constants.DB_PREFIX + "user")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private IdentityCard identityCard;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = Constants.DB_PREFIX + "user_reservation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private List<Reservation> reservationList;
}
