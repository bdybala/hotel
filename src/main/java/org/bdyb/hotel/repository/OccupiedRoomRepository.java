package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupiedRoomRepository extends JpaRepository<Visit, Long> {
}
