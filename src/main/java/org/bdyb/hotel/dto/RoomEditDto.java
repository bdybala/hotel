package org.bdyb.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomEditDto {
    private Long id;
    private String number;
    private Integer maxCapacity;
    private String roomTypeName;
}
