package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;
import org.bdyb.hotel.enums.ReservationStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = Constants.DB_PREFIX + "Reservation")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
    private Date since;
    private Date upTo;
    private Double price;

    @ManyToMany(mappedBy = "reservationList")
    private List<Customer> customers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
