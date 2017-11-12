package org.bdyb.hotel.init;

import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.dto.RegisterNewUserDto;
import org.bdyb.hotel.enums.IdCardType;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitUser {

    @Autowired
    private UserService userService;

    public void init() {
        String email = "admin@admin.pl";
        try {
            userService.register(RegisterNewUserDto.builder()
                    .email(email)
                    .password("123456")
                    .name("FirstName")
                    .surname("LastName")
                    .idCardType(IdCardType.ID_CARD)
                    .idCardNumber("AWK707070")
                    .monthExpiring(6)
                    .yearExpiring(2018)
                    .build());
            log.info("INIT User " + email + " created");
        } catch (ConflictException e) {
            log.info("INIT User " + email + " exists");
        }

    }
}
