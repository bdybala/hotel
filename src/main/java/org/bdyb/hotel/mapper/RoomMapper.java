package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper implements EntityMapper<Room,RoomDto> {

    @Autowired
    private RoomRepository roomRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public RoomDto mapToDto(Room room) {
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public Room mapToEntity(RoomDto roomDto) {
        if (roomDto.getId() != null) {
            Room room = roomRepository.findOne(roomDto.getId());
            if (room != null) return room;
        }
        return modelMapper.map(roomDto, Room.class);
    }
}
