package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;
import org.bdyb.hotel.enums.RoomType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Constants.DB_PREFIX + "Rooms")
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
    @Column(unique = true)
    private String number;
    private Integer capacity;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @OneToMany(
            mappedBy = "room",
            orphanRemoval = true)
    private List<RoomStatus> roomStatuses;

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
