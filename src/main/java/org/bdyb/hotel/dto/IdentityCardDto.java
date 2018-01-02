package org.bdyb.hotel.dto;

import lombok.*;
import org.bdyb.hotel.enums.IdCardType;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IdentityCardDto {

    private Long id;
    private IdCardType type;
    private String number;
    private Date expiringDate;
    private Date createdTime;
    private String createdBy;
}
