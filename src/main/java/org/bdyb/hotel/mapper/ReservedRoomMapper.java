package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.ReservedRoom;
import org.bdyb.hotel.dto.ReservedRoomDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservedRoomMapper implements EntityMapper<ReservedRoom, ReservedRoomDto> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public ReservedRoomDto mapToDto(ReservedRoom reservedRoom) {
        return mapper.map(reservedRoom, ReservedRoomDto.class);
    }

    @Override
    public ReservedRoom mapToEntity(ReservedRoomDto reservedRoomDto) {
        return mapper.map(reservedRoomDto, ReservedRoom.class);
    }
}
