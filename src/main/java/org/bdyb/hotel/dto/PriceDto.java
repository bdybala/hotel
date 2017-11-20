package org.bdyb.hotel.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PriceDto {

    private Long id;
    private Double value;
    private Date validSince;
    private Date validUpTo;

    private RoomDto room;
}
