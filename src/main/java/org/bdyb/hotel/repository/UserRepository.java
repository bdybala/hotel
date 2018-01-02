package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
