package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.ReservedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedRoomRepository extends JpaRepository<ReservedRoom, Long> {
}
