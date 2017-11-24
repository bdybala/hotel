package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;
import org.bdyb.hotel.enums.IdCardType;

import javax.persistence.*;


@Entity
@Table(name = Constants.DB_PREFIX + "IdentityCards")
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
    private IdCardType idCardType;
    @Column(unique = true)
    private String idCardNumber;
    private Integer monthExpiring;
    private Integer yearExpiring;
}
