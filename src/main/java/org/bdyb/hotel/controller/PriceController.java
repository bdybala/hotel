package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.Price;
import org.bdyb.hotel.dto.PriceDto;
import org.bdyb.hotel.dto.RoomIdAndDay;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.service.PriceService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
