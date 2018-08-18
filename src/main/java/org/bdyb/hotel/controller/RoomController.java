package org.bdyb.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.dto.pagination.PaginationDto;
import org.bdyb.hotel.exceptions.badRequest.SearchFieldNotExistingException;
import org.bdyb.hotel.exceptions.badRequest.SortFieldNotExistingException;
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
}
