package org.bdyb.hotel.domain;

import lombok.*;
import org.bdyb.hotel.config.Constants;
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
@Table(name = Constants.DB_PREFIX + "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    @Builder.Default
    private Date createdTime = new Date();
    @CreatedBy
    private String createdBy;

    private Double value;
    private Integer dayOfMonth;
    private Integer month;
    private Integer year;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "rooms_id")
    private Room room;
}
