package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.ReservedRoom;
import org.bdyb.hotel.dto.ReservedRoomDto;

import java.util.List;

public interface ReservedRoomService extends CrudService<ReservedRoom, ReservedRoomDto> {
    List<ReservedRoomDto> findByCustomerId(Long customerId);

    List<ReservedRoomDto> findBetweenTwoDates(Long sinceEpoch, Long toEpoch);
}
