package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
}
