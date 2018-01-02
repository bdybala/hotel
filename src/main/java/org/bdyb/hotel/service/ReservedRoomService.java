package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.ReservedRoom;
import org.bdyb.hotel.dto.ReservedRoomDto;
import org.joda.time.DateTime;

import java.util.List;

public interface ReservedRoomService extends CrudService<ReservedRoom, ReservedRoomDto> {
    List<ReservedRoomDto> findByCustomerId(Long customerId);
    List<ReservedRoomDto> findBetweenTwoDates(DateTime since, DateTime to);
}
