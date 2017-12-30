package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.ReservedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservedRoomRepository extends JpaRepository<ReservedRoom, Long> {
    List<ReservedRoom> findByCustomerId(Long customerId);
}
