package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    Boolean existsByName(String name);

    Optional<RoomType> findByName(String name);
}
