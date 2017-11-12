package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;
import org.bdyb.hotel.enums.RoomStatus;
import org.bdyb.hotel.enums.RoomType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Constants.DB_PREFIX + "Room")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private RoomStatus roomStatus;


    @OneToMany(
            mappedBy = "room",
            orphanRemoval = true
    )
    private List<Price> prices;

    @OneToMany(
            mappedBy = "room",
            orphanRemoval = true
    )
    private List<Reservation> reservations;
}
