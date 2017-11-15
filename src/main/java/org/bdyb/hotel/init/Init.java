package org.bdyb.hotel.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class Init {

    @Autowired
    InitUser initUser;

    @PostConstruct
    public void initAll() {
        initUser.init();
    }

}
