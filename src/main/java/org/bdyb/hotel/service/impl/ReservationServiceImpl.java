package org.bdyb.hotel.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.domain.Reservation;
import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.NewReservationDto;
import org.bdyb.hotel.dto.ReservationDto;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.exceptions.notFound.EntityNotFoundException;
import org.bdyb.hotel.repository.ReservationRepository;
import org.bdyb.hotel.repository.RoomRepository;
import org.bdyb.hotel.service.ReservationService;
import org.bdyb.hotel.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

  private final RoomRepository roomRepository;
  private final ReservationRepository reservationRepository;

  @Override
  public void addReservation(NewReservationDto reservationDto) throws EntityNotFoundException {
    Room room = roomRepository.findById(reservationDto.getRoomId())
        .orElseThrow(EntityNotFoundException::new);

    reservationRepository.save(Reservation.builder()
        .createdTime(new Date())
        .firstName(reservationDto.getFirstName())
        .lastName(reservationDto.getLastName())
        .email(reservationDto.getEmail())
        .since(DateTimeUtils.truncateHours(reservationDto.getSince()))
        .upTo(DateTimeUtils.truncateHours(reservationDto.getUpTo()))
        .room(room)
        .build());
  }

  @Override
  public List<ReservationDto> getAll() {
    List<Reservation> all = reservationRepository.findAll();
    return all.stream().map(entity -> ReservationDto.builder()
        .firstName(entity.getFirstName())
        .lastName(entity.getLastName())
        .email(entity.getEmail())
        .room(RoomDto.builder()
            .number(entity.getRoom().getNumber())
            .build())
        .since(entity.getSince())
        .upTo(entity.getUpTo())
        .build())
        .collect(Collectors.toList());
  }
}
