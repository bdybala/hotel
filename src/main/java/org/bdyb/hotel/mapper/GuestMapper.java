package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Guest;
import org.bdyb.hotel.dto.GuestDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GuestMapper implements EntityMapper<Guest, GuestDto> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public GuestDto mapToDto(Guest guest) {
        return mapper.map(guest, GuestDto.class);
    }

    @Override
    public Guest mapToEntity(GuestDto guestDto) {
        return mapper.map(guestDto, Guest.class);
    }
}
