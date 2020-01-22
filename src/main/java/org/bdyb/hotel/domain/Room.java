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
    @Builder.Default
    private Date createdTime = new Date();
    @CreatedBy
    private String createdBy;

    @Column(unique = true)
    private String number;
    private Integer maxCapacity;
    @Builder.Default
    private boolean isFree = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_types_id")
    private RoomType roomType;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Visit> visits;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Reservation> reservations;


}
