package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.enums.IdCardType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IdentityCard {

    private Long id;
    @Enumerated(EnumType.STRING)
    private IdCardType idCardEnum;
    private String idCardNumber;
}
