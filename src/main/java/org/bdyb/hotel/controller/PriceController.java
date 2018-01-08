package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.Price;
import org.bdyb.hotel.dto.PriceDto;
import org.bdyb.hotel.dto.RoomIdAndDay;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.service.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceController extends AbstractCrudController<Price, PriceDto> {

    public PriceController(PriceService service) {
        super(service);
    }

    @RequestMapping(value = "/byRoom", method = RequestMethod.POST)
    public PriceDto findByRoomIdAndDay(@RequestBody RoomIdAndDay roomIdAndDay) throws EntityNotFoundException {
        return ((PriceService) service).findByRoomIdAndDay(roomIdAndDay);
    }

    @RequestMapping(value = "/byRoom/{id}", method = RequestMethod.GET)
    public List<PriceDto> findByRoomId(@PathVariable("id") Long roomId) {
        return ((PriceService) service).findByRoomId(roomId);
    }

    @RequestMapping(value = "/byRoomAndInterval", method = RequestMethod.GET)
    public List<PriceDto> findByRoomId(@RequestParam("roomId") Long roomId,
                                       @RequestParam("since") Long since,
                                       @RequestParam("to") Long to) {
        return ((PriceService) service).findByRoomAndInterval(roomId, since, to);
    }

}
