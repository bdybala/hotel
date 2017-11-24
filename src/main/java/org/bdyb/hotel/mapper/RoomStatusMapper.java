package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.RoomStatus;
import org.bdyb.hotel.dto.RoomStatusDto;
import org.bdyb.hotel.repository.RoomStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomStatusMapper implements EntityMapper<RoomStatus, RoomStatusDto> {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private RoomStatusRepository roomStatusRepository;

    @Override
    public RoomStatusDto mapToDto(RoomStatus roomStatus) {
        return modelMapper.map(roomStatus, RoomStatusDto.class);
    }

    @Override
    public RoomStatus mapToEntity(RoomStatusDto roomStatusDto) {
        if (roomStatusDto.getId() != null) {
            RoomStatus roomStatus = roomStatusRepository.findOne(roomStatusDto.getId());
            if (roomStatus != null) return roomStatus;
        }
        return modelMapper.map(roomStatusDto, RoomStatus.class);
    }
}
