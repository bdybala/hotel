package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.enums.RoomStatus;
import org.bdyb.hotel.enums.RoomType;

import javax.persistence.*;

@Entity
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
    private Double price;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private RoomStatus roomStatus;

}
