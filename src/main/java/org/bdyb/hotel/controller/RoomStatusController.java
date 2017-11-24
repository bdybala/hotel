package org.bdyb.hotel.controller;

import org.bdyb.hotel.dto.RoomIdAndDateDto;
import org.bdyb.hotel.dto.RoomStatusDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.service.RoomStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomStatuses")
public class RoomStatusController {

    @Autowired
    private RoomStatusService roomStatusService;

    @RequestMapping(method = RequestMethod.GET)
    public List<RoomStatusDto> findAll() {
        return roomStatusService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RoomStatusDto findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
        return roomStatusService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public RoomStatusDto addOne(@RequestBody RoomStatusDto newRoomStatus) throws ConflictException {
        return roomStatusService.addOne(newRoomStatus);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public RoomStatusDto editOne(@RequestBody RoomStatusDto editedRoomStatus) throws ConflictException, EntityNotFoundException {
        return roomStatusService.editOne(editedRoomStatus);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RoomStatusDto deleteOne(@PathVariable("id") Long id) throws EntityNotFoundException {
        return roomStatusService.delete(id);
    }

    @RequestMapping(value = "/byRoom/{roomId}", method = RequestMethod.GET)
    public List<RoomStatusDto> findByRoom(@PathVariable("roomId") Long roomId) {
        return roomStatusService.findByRoomId(roomId);
    }

    @RequestMapping(value = "/byRoomAndDate", method = RequestMethod.POST)
    public RoomStatusDto findByRoomAndDate(@RequestBody RoomIdAndDateDto roomIdAndDateDto) { return roomStatusService.findByRoomIdAndDate(roomIdAndDateDto); }
}
