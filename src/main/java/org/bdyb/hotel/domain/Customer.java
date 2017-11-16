package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "customer")
@Table(name = Constants.DB_PREFIX + "customer")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date birthday;
    private String name;
    private String surname;

    @OneToOne(cascade = CascadeType.ALL)
    private IdentityCard identityCard;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = Constants.DB_PREFIX + "customer_reservation",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private List<Reservation> reservationList;
}
