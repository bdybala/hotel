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
public class GuestDto {

    private Long id;
    private Date since;
    private Date upTo;
    private Date createdTime;
    private String createdBy;

    private Set<OccupiedRoomDto> occupiedRooms;
}
