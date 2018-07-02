package org.bdyb.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityRequestDto {
    private Date since;
    private Date upTo;
    private String roomTypeName;
    private Integer maxCapacity;
}
