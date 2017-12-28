package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.RoomDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper implements EntityMapper<Room, RoomDto> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public RoomDto mapToDto(Room room) {
        return mapper.map(room, RoomDto.class);
    }

    @Override
    public Room mapToEntity(RoomDto roomDto) {
        return mapper.map(roomDto, Room.class);
    }
}
