package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.Price;
import org.bdyb.hotel.dto.PriceDto;
import org.bdyb.hotel.dto.RoomIdAndDay;
import org.bdyb.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface PriceService extends CrudService<Price, PriceDto> {

    PriceDto findByRoomIdAndDay(RoomIdAndDay roomIdAndDay) throws EntityNotFoundException;

    List<PriceDto> findByRoomId(Long roomId);

    List<PriceDto> findByRoomAndInterval(Long roomId, Long since, Long to);
}
