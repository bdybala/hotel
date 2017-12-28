package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.service.CrudService;
import org.bdyb.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomController extends AbstractCrudController<Room, RoomDto> {

    @Autowired
    protected RoomService service;

    public RoomController(CrudService<Room, RoomDto> service) {
        super(service);
    }
}
