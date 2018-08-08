package org.bdyb.hotel.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Long id;
    private String number;
    private Integer maxCapacity;
    private Boolean isFree;
    private String roomTypeName;
    private String roomTypeDescription;
}
