package org.bdyb.hotel.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@RequiredArgsConstructor
public class Init {


    private final InitRoomTypes initRoomTypes;
    private final InitRoles initRoles;
    private final InitUsers initUsers;

    @PostConstruct
    public void initAll() {
        initRoomTypes.init();
        initRoles.init();
        initUsers.init();
//        initRooms.init();
//        initCustomers.init();
    }

}
