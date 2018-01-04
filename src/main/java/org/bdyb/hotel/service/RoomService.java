package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.DateRange;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.dto.RoomWithTotalPriceDto;

import java.util.Date;
import java.util.List;

public interface RoomService extends CrudService<Room, RoomDto> {
    List<RoomDto> findAllFree(DateRange dateRange);

    List<RoomWithTotalPriceDto> findFreeByRoomType(Date since, Date to, String roomType);
}
