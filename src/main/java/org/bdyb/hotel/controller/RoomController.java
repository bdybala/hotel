package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.DateRange;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.dto.RoomWithTotalPriceDto;
import org.bdyb.hotel.service.CrudService;
import org.bdyb.hotel.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @RequestMapping(value = "/freeByRoomType", method = RequestMethod.GET)
    public List<RoomWithTotalPriceDto> findAllFreeByRoomType(
            @RequestParam("since") Long since,
            @RequestParam("to") Long to,
            @RequestParam("roomType") String roomType) {
        return ((RoomService) service).findFreeByRoomType(since, to, roomType);
    }
}
