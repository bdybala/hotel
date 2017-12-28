package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
