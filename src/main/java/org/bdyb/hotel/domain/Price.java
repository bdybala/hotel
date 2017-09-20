package org.bdyb.hotel.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static org.bdyb.hotel.config.Constants.DB_PREFIX;

@Entity
@Table(name = DB_PREFIX + "Price")
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
    private Date validUpto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
