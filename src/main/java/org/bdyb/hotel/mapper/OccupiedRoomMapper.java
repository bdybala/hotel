package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.OccupiedRoom;
import org.bdyb.hotel.dto.OccupiedRoomDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OccupiedRoomMapper implements EntityMapper<OccupiedRoom, OccupiedRoomDto> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public OccupiedRoomDto mapToDto(OccupiedRoom occupiedRoom) {
        return mapper.map(occupiedRoom, OccupiedRoomDto.class);
    }

    @Override
    public OccupiedRoom mapToEntity(OccupiedRoomDto occupiedRoomDto) {
        return mapper.map(occupiedRoomDto, OccupiedRoom.class);
    }
}
