package org.bdyb.hotel.init;

import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.enums.RoleNameEnum;
import org.bdyb.hotel.repository.RoleRepository;
import org.bdyb.hotel.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitUsers {

    private static final String EMAIL = "admin@admin.pl";
    private static final String PASSWORD = "123456";
    private static final String FIRST_NAME = "Volodymyr";
    private static final String LAST_NAME = "Putin";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void init() {
        for (int i = 0; i < 10; i++) {
            if (!userRepository.existsByEmail(i + EMAIL)) {
                User user = new User();
                user.setEmail(i + EMAIL);
                user.setPassword(PASSWORD);
                user.setFirstName(FIRST_NAME);
                user.setLastName(LAST_NAME);
                user.setRole(roleRepository.findByName(RoleNameEnum.ROLE_ADMINISTRATOR).get());
                userRepository.save(user);
            }
        }
    }
}
