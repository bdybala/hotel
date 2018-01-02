package org.bdyb.hotel.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class Init {

    @Autowired
    private InitRoomTypes initRoomTypes;
    @Autowired
    private InitRoles initRoles;
    @Autowired
    private InitRooms initRooms;
    @Autowired
    private InitCustomers initCustomers;
    @Autowired
    private InitUsers initUsers;

    @PostConstruct
    public void initAll() {
        initRoomTypes.init();
        initRoles.init();
        initRooms.init();
        initCustomers.init();
        initUsers.init();
    }

}
