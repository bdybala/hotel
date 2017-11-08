package org.bdyb.hotel.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;

import static org.bdyb.hotel.config.Constants.DB_PREFIX;

@Entity(name = "user")
@Table(name = DB_PREFIX + "user")
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
    private String email;
    private String name;

    @OneToOne
    private IdentityCard identityCard;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = DB_PREFIX + "user_reservation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =  @JoinColumn(name = "reservation_id")
    )
    private List<Reservation> reservationList;
}
