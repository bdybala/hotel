package org.bdyb.hotel.controller;

import org.bdyb.hotel.dto.DateRangeDto;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.exceptions.InternalServerErrorException;
import org.bdyb.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RoomDto findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
        return roomService.findOne(id);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<RoomDto> findAll() {
        return roomService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public RoomDto addOneUser(@RequestBody RoomDto newRoom) throws ConflictException {
        return roomService.addOne(newRoom);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public RoomDto editOneUser(@RequestBody RoomDto editedRoom) throws EntityNotFoundException, ConflictException {
        return roomService.editOne(editedRoom);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RoomDto deleteCustomer(@PathVariable Long id) throws InternalServerErrorException, EntityNotFoundException {
        return roomService.delete(id);
    }

    @RequestMapping(value = "/free", method = RequestMethod.POST)
    public List<RoomDto> findFreeInInterval(@RequestBody DateRangeDto dateRangeDto) {
        return roomService.findFreeInInterval(dateRangeDto);
    }
}
