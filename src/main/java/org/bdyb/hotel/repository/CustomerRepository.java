package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    Boolean existsByPesel(Long pesel);

    Customer findByIdentityCardNumber(String number);
}
