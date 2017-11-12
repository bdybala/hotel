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
    private IdCardType idCardType;
    private String idCardNumber;
    private Integer monthExpiring;
    private Integer yearExpiring;
}
