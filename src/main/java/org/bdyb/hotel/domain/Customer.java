package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Constants.DB_PREFIX + "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer pesel;
    private Date birthday;
    @CreatedDate
    private Date createdTime;
    @CreatedBy
    private String createdBy;

    @OneToOne(fetch = FetchType.LAZY)
    private IdentityCard identityCard;

    @ManyToMany(mappedBy = "customers")
    private Set<OccupiedRoom> occupiedRooms;
}
