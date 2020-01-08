package org.bdyb.hotel.service;

import java.util.List;
import org.bdyb.hotel.dto.NewReservationDto;
import org.bdyb.hotel.dto.ReservationDto;
import org.bdyb.hotel.exceptions.notFound.EntityNotFoundException;

public interface ReservationService {

  void addReservation(NewReservationDto reservationDto) throws EntityNotFoundException;

  List<ReservationDto> getAll();

}
