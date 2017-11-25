package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.Price;
import org.bdyb.hotel.dto.PriceDto;
import org.bdyb.hotel.dto.RoomIdAndDay;
import org.bdyb.hotel.exceptions.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public interface PriceService extends CrudService<Price, PriceDto> {

    List<PriceDto> findByRoomId(Long roomId) throws EntityNotFoundException;

    PriceDto findByRoomIdAndForDay(RoomIdAndDay roomIdAndDay) throws EntityNotFoundException;
}
