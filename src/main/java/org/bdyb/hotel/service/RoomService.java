package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.DateRangeDto;
import org.bdyb.hotel.dto.RoomDto;

import java.util.List;

public interface RoomService extends CrudService<Room, RoomDto> {

    List<RoomDto> findFreeInInterval(DateRangeDto dateRangeDto);
}
