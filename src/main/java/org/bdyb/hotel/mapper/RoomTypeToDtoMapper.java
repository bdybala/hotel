package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.RoomType;
import org.bdyb.hotel.dto.RoomTypeDto;
import org.springframework.stereotype.Component;

@Component
public class RoomTypeToDtoMapper implements EntityToDtoMapper<RoomType, RoomTypeDto> {
    @Override
    public RoomTypeDto mapToDto(RoomType roomType) {
        return RoomTypeDto.builder()
                .id(roomType.getId())
                .name(roomType.getName())
                .description(roomType.getDescription())
                .build();
    }
}
