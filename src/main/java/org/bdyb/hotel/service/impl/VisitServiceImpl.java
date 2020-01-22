package org.bdyb.hotel.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.domain.Reservation;
import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.domain.Visit;
import org.bdyb.hotel.dto.GuestDto;
import org.bdyb.hotel.dto.NewVisitDto;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.dto.VisitDto;
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

  @Override
  public List<VisitDto> getAll() {
    return visitRepository.findAll().stream().map(visit -> VisitDto.builder()
        .id(visit.getId())
        .room(mapRoom(visit.getRoom()))
        .guests(mapGuests(visit.getCustomers()))
        .since(visit.getSince())
        .upTo(visit.getUpTo())
        .build())
        .collect(Collectors.toList());
  }

  private List<GuestDto> mapGuests(Set<Customer> customers) {
    return customers.stream().map(customer -> GuestDto.builder()
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .pesel(customer.getPesel())
        .idCardNumber(customer.getIdCardNumber())
        .build())
        .collect(Collectors.toList());
  }

  private RoomDto mapRoom(Room room) {
    return RoomDto.builder()
        .number(room.getNumber())
        .roomTypeName(room.getRoomType().getName())
        .maxCapacity(room.getMaxCapacity())
        .build();
  }
}
