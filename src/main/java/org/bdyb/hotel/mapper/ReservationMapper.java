package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Reservation;
import org.bdyb.hotel.dto.ReservationDto;
import org.bdyb.hotel.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReservationMapper implements EntityMapper<Reservation, ReservationDto> {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public ReservationDto mapToDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }

    @Override
    public Reservation mapToEntity(ReservationDto reservationDto) {
        if (reservationDto.getId() != null) {
            Optional<Reservation> reservation = Optional.ofNullable(reservationRepository.findOne(reservationDto.getId()));
            if (reservation.isPresent()) return reservation.get();
        }
        return modelMapper.map(reservationDto, Reservation.class);
    }
}
