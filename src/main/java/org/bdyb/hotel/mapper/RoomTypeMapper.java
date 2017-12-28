package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.RoomType;
import org.bdyb.hotel.dto.RoomTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoomTypeMapper implements EntityMapper<RoomType, RoomTypeDto> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public RoomTypeDto mapToDto(RoomType roomType) {
        return mapper.map(roomType, RoomTypeDto.class);
    }

    @Override
    public RoomType mapToEntity(RoomTypeDto roomTypeDto) {
        return mapper.map(roomTypeDto, RoomType.class);
    }
}
