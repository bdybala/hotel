package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByNumber(String number);

    @Query(
            "select r from Room r left outer join r.occupiedRooms o left outer join r.reservedRooms rr " +
                    "where (o is null or ?1 > o.upTo or ?2 < o.since) AND " +
                    "(rr is null or ?1 > rr.upTo or ?2 < rr.since)"
    )
    List<Room> findAllFree(Date start, Date end);
}
