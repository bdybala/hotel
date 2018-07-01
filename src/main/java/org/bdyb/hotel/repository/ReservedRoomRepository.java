package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ReservedRoomRepository extends JpaRepository<Reservation, Long> {

//    @Query(
//            "select r from Reservation r where r.since between ?1 and ?2 or r.upTo between ?1 and ?2 or " +
//                    "?1 between r.since and r.upTo"
//    )
//    List<Reservation> findBetweenTwoDates(Date since, Date to);
}
