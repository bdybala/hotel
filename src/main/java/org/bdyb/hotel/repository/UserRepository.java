package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    User findByUsernameAndPassword(String username, String password);



    @Query(
            nativeQuery = true,
            value = "SELECT\n" +
                    " \tu.* \n" +
                    "FROM \n" +
                    "\thotel_users u\n" +
                    "    JOIN hotel_roles r ON u.roles_id = r.id\n" +
                    "WHERE \n" +
                    "\tu.username LIKE %?1% AND r.name LIKE %?2%"
    )
    List<User> findByUsernameAndRoleId(String username, String roleName);
}
