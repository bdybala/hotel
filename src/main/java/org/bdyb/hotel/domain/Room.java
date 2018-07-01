package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Constants.DB_PREFIX + "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private Date createdTime;
    @CreatedBy
    private String createdBy;

    @Column(unique = true)
    private String number;
    private Integer maxCapacity;
    private boolean isFree;
    private boolean isReserved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_types_id")
    private RoomType roomType;

    @OneToMany(mappedBy = "room")
    private List<Visit> visits;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;
}
