package org.bdyb.hotel.dto;

import lombok.*;
import org.bdyb.hotel.domain.IdentityCard;
import org.bdyb.hotel.domain.Reservation;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;

    private IdentityCardDto identityCardDto;
}
