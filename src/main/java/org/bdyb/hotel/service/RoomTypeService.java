package org.bdyb.hotel.service;

import org.bdyb.hotel.dto.RoomTypeDto;

import java.util.List;

public interface RoomTypeService {
    List<RoomTypeDto> findAll();
}
