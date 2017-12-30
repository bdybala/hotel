package org.bdyb.hotel.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservedRoomDto {

    private Long id;
    private Date since;
    private Date upTo;

    private RoomDto room;
    private CustomerDto customer;
}
