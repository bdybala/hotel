package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
