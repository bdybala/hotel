package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
