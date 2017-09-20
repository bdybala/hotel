package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.enums.ReservationStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
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
    private Price price;

    private List<Customer> customersList;
    private Room room;
}
