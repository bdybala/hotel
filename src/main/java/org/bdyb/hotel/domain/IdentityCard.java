package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.enums.IdCardType;

import javax.persistence.*;

import static org.bdyb.hotel.config.Constants.DB_PREFIX;

@Entity
@Table(name = DB_PREFIX + "IdentityCard")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IdentityCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private IdCardType idCardEnum;
    private String idCardNumber;
}
