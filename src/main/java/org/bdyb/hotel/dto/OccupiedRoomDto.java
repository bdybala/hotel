package org.bdyb.hotel.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OccupiedRoomDto {

    private Long id;
    private Date since;
    private Date upTo;
    private Date createdTime;
    private String createdBy;

    private RoomDto room;

    private Set<GuestDto> guests;
}
