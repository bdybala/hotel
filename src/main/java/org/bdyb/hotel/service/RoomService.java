package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.AvailabilityRequestDto;
import org.bdyb.hotel.dto.NewRoomDto;
import org.bdyb.hotel.exceptions.conflict.RoomAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoomTypeNotFoundException;

import java.util.List;

public interface RoomService {
    Room createNewRoom(NewRoomDto newRoomDto) throws RoomAlreadyExistsConflictException, RoomTypeNotFoundException;
    List<Room> findAvailableRooms(AvailabilityRequestDto availabilityRequestDto) throws RoomTypeNotFoundException;
}
