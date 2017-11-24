package org.bdyb.hotel.dto;

import lombok.*;
import org.bdyb.hotel.enums.RoomStatusEnum;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoomStatusDto {

    private Long id;
    private Date since;
    private Date upTo;
    private RoomStatusEnum name;

    private RoomDto room;
}
