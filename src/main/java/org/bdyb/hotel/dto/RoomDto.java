package org.bdyb.hotel.dto;

import lombok.*;
import org.bdyb.hotel.domain.RoomType;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomDto {

    private Long id;
    private String number;
    private Integer maxCapacity;
    private Date createdTime;
    private String createdBy;

    private RoomTypeDto roomType;
}
