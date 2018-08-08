package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.RoomDto;
import org.springframework.stereotype.Component;

@Component
public class RoomToDtoMapper implements EntityToDtoMapper<Room, RoomDto> {
    @Override
    public RoomDto mapToDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .number(room.getNumber())
                .maxCapacity(room.getMaxCapacity())
                .isFree(room.isFree())
                .roomTypeDescription(room.getRoomType().getDescription())
                .roomTypeName(room.getRoomType().getName())
                .build();
    }
}
