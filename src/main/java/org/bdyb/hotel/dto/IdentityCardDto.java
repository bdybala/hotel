package org.bdyb.hotel.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IdentityCardDto {

    private Long id;
    private String number;
    private Date expiringDate;
    private Date createdTime;
    private String createdBy;
}
