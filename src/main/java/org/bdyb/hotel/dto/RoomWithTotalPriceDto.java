package org.bdyb.hotel.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomWithTotalPriceDto {

    private Long roomId;
    private Integer maxCapacity;
    private String roomNumber;
    private String roomType;
    private Double totalPrice;
}
