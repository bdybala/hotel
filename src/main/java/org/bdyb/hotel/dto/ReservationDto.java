package org.bdyb.hotel.dto;

import lombok.*;
import org.bdyb.hotel.enums.ReservationStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDto {

    private Long id;
    private ReservationStatus reservationStatus;
    private Date since;
    private Date upTo;
    private Double price;

    private List<CustomerDto> customers;
    private RoomDto room;
}
