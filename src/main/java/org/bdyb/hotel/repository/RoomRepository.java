package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByNumber(String number);

    @Query(
            "select r from Room r join r.roomStatuses rs " +
                    "where rs is null or " +
                    "rs.since > ?1 and rs.since > ?2 or " +
                    "rs.upTo < ?1 and rs.upTo < ?2"
    )
    List<Room> findFreeInInterval(Date from, Date to);
}
