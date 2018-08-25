package org.bdyb.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityRequestDto {
    @NotNull
    private Date since;
    @NotNull
    private Date upTo;
    private String roomTypeName;
    private Integer maxCapacity;
    private Integer currentPage;
    private Integer pageSize;
}
