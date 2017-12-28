package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByRoomId(@NotNull Long roomId);

    @Query(
            "select p from Price p " +
                    "where p.room.id = ?1 and ?2 between p.since and p.upTo"
    )
    Price findByRoomIdAndForDay(@NotNull Long roomId, @NotNull Date forDay);
}
