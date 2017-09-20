package org.bdyb.hotel.dto;

import lombok.*;
import org.bdyb.hotel.enums.IdCardType;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IdentityCardDto {
    private Long id;
    private IdCardType idCardEnum;
    private String idCardNumber;
}
