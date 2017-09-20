package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.IdentityCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityCardRepository extends JpaRepository<IdentityCard, Long> {
}
