package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.DateRange;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.service.CrudService;
import org.bdyb.hotel.service.RoomService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController extends AbstractCrudController<Room, RoomDto> {

    public RoomController(CrudService<Room, RoomDto> service) {
        super(service);
    }

    @RequestMapping(value = "/free", method = RequestMethod.POST)
    public List<RoomDto> findAllFree(@RequestBody DateRange dateRange) {
        return ((RoomService) service).findAllFree(dateRange);
    }
}
