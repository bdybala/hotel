package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;
import org.bdyb.hotel.enums.IdCardType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;


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
    @CreatedDate
    private Date createdTime;
    @CreatedBy
    private String createdBy;

    @Enumerated(EnumType.STRING)
    private IdCardType type;
    private String number;
    private Date expiringDate;

}
