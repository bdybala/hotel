package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.AvailabilityRequestDto;
import org.bdyb.hotel.dto.NewRoomDto;
import org.bdyb.hotel.dto.RoomPaginationResponseDto;
import org.bdyb.hotel.dto.pagination.AvailabilityResponseDto;
import org.bdyb.hotel.dto.pagination.PaginationDto;
import org.bdyb.hotel.exceptions.badRequest.SearchFieldNotExistingException;
import org.bdyb.hotel.exceptions.badRequest.SortFieldNotExistingException;
import org.bdyb.hotel.exceptions.conflict.RoomAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoomIdNotFoundException;
import org.bdyb.hotel.exceptions.notFound.RoomTypeNotFoundException;

import java.util.List;

public interface RoomService {
    Room createNewRoom(NewRoomDto newRoomDto) throws RoomAlreadyExistsConflictException, RoomTypeNotFoundException;

    AvailabilityResponseDto findAvailableRooms(AvailabilityRequestDto availabilityRequestDto) throws RoomTypeNotFoundException;

    RoomPaginationResponseDto searchRooms(PaginationDto roomPaginationDto) throws SearchFieldNotExistingException, SortFieldNotExistingException;

    void deleteById(Long id) throws RoomIdNotFoundException;
}
