package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Constants.DB_PREFIX + "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Email
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @CreatedDate
    private Date createdTime;
    @CreatedBy
    private String createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roles_id")
    private Role role;
}
