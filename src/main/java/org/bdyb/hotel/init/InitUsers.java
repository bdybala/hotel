package org.bdyb.hotel.init;

import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.service.RoleService;
import org.bdyb.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitUsers {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    private String roleAdministrator = "ROLE_ADMINISTRATOR";

    public void init() {

        String username = "admin";
        String password = "123456";
        String email = "admin@admin.pl";

        try {
            userService.addOne(UserDto.builder()
                    .email(email)
                    .username(username)
                    .password(password)
                    .role(roleService.findByName(roleAdministrator))
                    .build());
            log.info("INIT User " + username + " created");
        } catch (ConflictException e) {
            log.info("INIT User " + username + " exists");
        } catch (EntityNotFoundException e) {
            log.info("INIT User : " + "role " + roleAdministrator + " not found!");
        }

    }
}
