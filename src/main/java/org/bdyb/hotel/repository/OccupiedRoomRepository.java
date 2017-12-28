package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.OccupiedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupiedRoomRepository extends JpaRepository<OccupiedRoom, Long> {
}
