package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Price;
import org.bdyb.hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface PriceRepository extends JpaRepository<Price, Long> {

    long countAllByRoomAndDayGreaterThanEqualAndDayLessThan(Room room, Date since, Date upTo);

//    @Query(
//            "select p from Price p " +
//                    "where p.room.id = ?1 and ?2 between p.since and p.upTo"
//    )
//    Price findByRoomIdAndForDay(@NotNull Long roomId, @NotNull Date forDay);

//    @Query(
//            "select p from Price p " +
//                    "where p.room.id = ?1 and " +
//                    "(p.since between ?2 and ?3 or " +
//                    "p.upTo between ?2 and ?3 or ?2 between p.since and p.upTo)"
//    )
//    List<Price> findByRoomIdAndInterval(Long roomId, Date since, Date to);
}
