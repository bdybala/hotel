package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.enums.ReservationStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static org.bdyb.hotel.config.Constants.DB_PREFIX;

@Entity
@Table(name = DB_PREFIX + "Reservation")
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
    private ReservationStatus reservationStatus;
    private Date since;
    private Date upTo;
    private Double price;

    @ManyToMany(mappedBy = "reservationList")
    private List<Customer> customersList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
