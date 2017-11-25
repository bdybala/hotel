package org.bdyb.hotel.controller;

import org.bdyb.hotel.dto.PriceDto;
import org.bdyb.hotel.dto.RoomIdAndDay;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @RequestMapping(method = RequestMethod.GET)
    public List<PriceDto> findAll() {
        return priceService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PriceDto findOne(@PathVariable("id") Long priceId) throws EntityNotFoundException {
        return priceService.findOne(priceId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public PriceDto addOne(@RequestBody PriceDto priceDto) throws ConflictException {
        return priceService.addOne(priceDto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public PriceDto editOne(@RequestBody PriceDto priceDto) throws ConflictException, EntityNotFoundException {
        return priceService.editOne(priceDto);
    }

    @RequestMapping(value = "/{id]", method = RequestMethod.DELETE)
    public PriceDto deleteOne(@PathVariable("id") Long priceId) throws EntityNotFoundException {
        return priceService.delete(priceId);
    }

    @RequestMapping(value = "/byRoom/{roomId}", method = RequestMethod.GET)
    public List<PriceDto> findByRoomId(@PathVariable("roomId") Long roomId) throws EntityNotFoundException {
        return priceService.findByRoomId(roomId);
    }

    @RequestMapping(value = "/byRoom", method = RequestMethod.POST)
    public PriceDto findByRoomId(@RequestBody RoomIdAndDay roomIdAndDay) throws EntityNotFoundException {
        return priceService.findByRoomIdAndForDay(roomIdAndDay);
    }
}
