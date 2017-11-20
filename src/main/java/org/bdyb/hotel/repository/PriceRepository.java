package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByRoomId(@NotNull Long roomId);
}
