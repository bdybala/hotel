package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long> {

    List<RoomStatus> findByRoomId(@NotNull Long roomId);

    @Query("select rs from RoomStatus rs where rs.room.id = ?1 AND (?2 between rs.since and rs.upTo)")
    RoomStatus findByRoomIdAndDate(@NotNull Long roomId, @NotNull Date date);
}
