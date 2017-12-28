package org.bdyb.hotel.dto;

import lombok.*;
import org.bdyb.hotel.domain.Room;

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
    private Date since;
    private Date upTo;
    private Date createdTime;
    private String createdBy;

    private Room room;
}
