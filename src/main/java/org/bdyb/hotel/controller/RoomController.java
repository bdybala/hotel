package org.bdyb.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.dto.AvailabilityRequestDto;
import org.bdyb.hotel.dto.NewRoomDto;
import org.bdyb.hotel.dto.pagination.PaginationDto;
import org.bdyb.hotel.exceptions.badRequest.SearchFieldNotExistingException;
import org.bdyb.hotel.exceptions.badRequest.SortFieldNotExistingException;
import org.bdyb.hotel.exceptions.conflict.RoomAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoomIdNotFoundException;
import org.bdyb.hotel.exceptions.notFound.RoomTypeNotFoundException;
import org.bdyb.hotel.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @PostMapping(value = "/search")
    public ResponseEntity searchRooms(@RequestBody PaginationDto roomPaginationDto) throws SearchFieldNotExistingException, SortFieldNotExistingException {
        return new ResponseEntity<>(
                roomService.searchRooms(roomPaginationDto != null ? roomPaginationDto : new PaginationDto()),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addNewRoom(@RequestBody NewRoomDto newRoomDto) throws RoomTypeNotFoundException, RoomAlreadyExistsConflictException {
        roomService.createNewRoom(newRoomDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) throws RoomIdNotFoundException {
        roomService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/availability")
    public ResponseEntity findAvailableRooms(@RequestBody AvailabilityRequestDto availabilityRequestDto) throws RoomTypeNotFoundException {
        return new ResponseEntity<>(roomService.findAvailableRooms(availabilityRequestDto), HttpStatus.OK);
    }
}
