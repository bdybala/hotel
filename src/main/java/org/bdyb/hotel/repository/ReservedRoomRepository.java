package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.ReservedRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ReservedRoomRepository extends JpaRepository<ReservedRoom, Long> {
    List<ReservedRoom> findByCustomerId(Long customerId);

    @Query(
            "select r from ReservedRoom r where r.since between ?1 and ?2 or r.upTo between ?1 and ?2"
    )
    List<ReservedRoom> findBetweenTwoDates(Date since, Date to);
}
