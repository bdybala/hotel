package org.bdyb.hotel.dto;

import lombok.*;
import org.bdyb.hotel.enums.RoomStatus;
import org.bdyb.hotel.enums.RoomType;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    private Long id;
    private String number;
    private Integer capacity;
    private RoomType roomType;
    private RoomStatus roomStatus;
}
