package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.RoomStatus;
import org.bdyb.hotel.dto.RoomIdAndDateDto;
import org.bdyb.hotel.dto.RoomStatusDto;

import java.util.List;

public interface RoomStatusService extends CrudService<RoomStatus, RoomStatusDto> {

    List<RoomStatusDto> findByRoomId(Long roomId);

    RoomStatusDto findByRoomIdAndDate(RoomIdAndDateDto roomIdAndDateDto);
}
