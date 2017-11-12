package org.bdyb.hotel.init;

import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitUser {

    @Autowired
    private UserService userService;

    public void init() {

    }
}
