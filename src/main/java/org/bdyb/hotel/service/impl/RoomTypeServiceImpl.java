package org.bdyb.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.dto.RoomTypeDto;
import org.bdyb.hotel.mapper.RoomTypeToDtoMapper;
import org.bdyb.hotel.repository.RoomTypeRepository;
import org.bdyb.hotel.service.RoomTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeToDtoMapper roomTypeToDtoMapper;

    @Override
    public List<RoomTypeDto> findAll() {
        return roomTypeToDtoMapper.mapToDto(roomTypeRepository.findAll());
    }
}
