package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
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
    @CreatedDate
    private Date createdTime;
    @CreatedBy
    private String createdBy;

    private String firstName;
    private String lastName;
    private Long pesel;
    private Date birthday;
    private String email;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private IdentityCard identityCard;

    @ManyToMany(mappedBy = "customers")
    private Set<Visit> visits;
}
