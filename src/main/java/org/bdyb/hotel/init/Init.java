package org.bdyb.hotel.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class Init {

    @Autowired
    InitCustomer initCustomer;
    @Autowired
    InitRoom initRoom;

    @PostConstruct
    public void initAll() {
        initCustomer.init();
        initRoom.init();
    }

}
