package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Boolean existsByPesel(Integer pesel);
}
