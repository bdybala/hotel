package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.NewRoomDto;
import org.bdyb.hotel.exceptions.conflict.RoomAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoomTypeNotFoundException;

public interface RoomService {
    Room createNewRoom(NewRoomDto newRoomDto) throws RoomAlreadyExistsConflictException, RoomTypeNotFoundException;
}
