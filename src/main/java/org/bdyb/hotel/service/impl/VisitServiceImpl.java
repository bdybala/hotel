package org.bdyb.hotel.service.impl;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.domain.Reservation;
import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.domain.Visit;
import org.bdyb.hotel.dto.NewVisitDto;
import org.bdyb.hotel.exceptions.notFound.ReservationNotFoundException;
import org.bdyb.hotel.repository.ReservationRepository;
import org.bdyb.hotel.repository.VisitRepository;
import org.bdyb.hotel.service.VisitService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

  private final ReservationRepository reservationRepository;
  private final VisitRepository visitRepository;

  @Override
  public void addVisit(NewVisitDto newVisitDto) throws ReservationNotFoundException {
    log.info(String.valueOf(newVisitDto));
    Reservation reservation = reservationRepository.findById(newVisitDto.getReservationId())
        .orElseThrow(ReservationNotFoundException::new);
    Room room = reservation.getRoom();

    Visit visit = Visit.builder()
        .room(room)
        .since(reservation.getSince())
        .upTo(reservation.getUpTo())
        .customers(
            newVisitDto.getGuests().stream().map(guestDto -> Customer.builder()
                .firstName(guestDto.getFirstName())
                .lastName(guestDto.getLastName())
                .pesel(guestDto.getPesel())
                .idCardNumber(guestDto.getIdCardNumber())
                .build()).collect(
                Collectors.toSet()))
        .build();

    reservation.setCheckedIn(true);
    Visit saved = visitRepository.save(visit);

    log.info(String.valueOf(saved));
  }
}
