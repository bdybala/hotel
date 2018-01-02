package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Boolean existsByName(String name);

    Role findByName(String name);
}
