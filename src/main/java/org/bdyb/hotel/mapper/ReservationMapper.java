package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Reservation;
import org.bdyb.hotel.dto.ReservationDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper implements EntityMapper<Reservation, ReservationDto> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public ReservationDto mapToDto(Reservation reservation) {
        return mapper.map(reservation, ReservationDto.class);
    }

    @Override
    public Reservation mapToEntity(ReservationDto reservationDto) {
        return mapper.map(reservationDto, Reservation.class);
    }
}
