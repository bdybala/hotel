package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = Constants.DB_PREFIX + "Price")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double value;
    private Date validSince;
    private Date validUpTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
