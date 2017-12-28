package org.bdyb.hotel.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer pesel;
    private Date birthday;
    private Date createdTime;
    private String createdBy;

    private IdentityCardDto identityCard;
}
