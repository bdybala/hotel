package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;
import org.bdyb.hotel.enums.RoomStatusEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Constants.DB_PREFIX + "RoomStatuses")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoomStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date since;
    private Date upTo;
    @Enumerated(EnumType.STRING)
    private RoomStatusEnum name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
}
