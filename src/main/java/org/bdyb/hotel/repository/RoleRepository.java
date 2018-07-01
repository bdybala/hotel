package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Role;
import org.bdyb.hotel.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Boolean existsByName(RoleNameEnum name);

    Optional<Role> findByName(RoleNameEnum roleName);
}
