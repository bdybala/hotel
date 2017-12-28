package org.bdyb.hotel.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDto {

    private Long id;
    private Date createdTime;
    private String createdBy;

    private CustomerDto customer;
}
