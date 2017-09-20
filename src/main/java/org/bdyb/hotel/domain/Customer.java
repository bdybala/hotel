package org.bdyb.hotel.domain;

import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static org.bdyb.hotel.config.Constants.DB_PREFIX;

@Entity(name = "customer")
@Table(name = DB_PREFIX + "customer")
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
    private String name;

    @OneToOne
    private IdentityCard identityCard;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = DB_PREFIX + "customer_reservation",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns =  @JoinColumn(name = "reservation_id")
    )
    private List<Reservation> reservationList;
}
