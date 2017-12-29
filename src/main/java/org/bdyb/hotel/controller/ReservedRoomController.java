package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.ReservedRoom;
import org.bdyb.hotel.dto.ReservedRoomDto;
import org.bdyb.hotel.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservedRooms")
public class ReservedRoomController extends AbstractCrudController<ReservedRoom, ReservedRoomDto> {

    public ReservedRoomController(CrudService<ReservedRoom, ReservedRoomDto> service) {
        super(service);
    }
}
