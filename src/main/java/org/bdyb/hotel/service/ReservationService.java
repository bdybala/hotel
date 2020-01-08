package org.bdyb.hotel.service;

import org.bdyb.hotel.dto.ReservationDto;
import org.bdyb.hotel.exceptions.notFound.EntityNotFoundException;

public interface ReservationService {

  void addReservation(ReservationDto reservationDto) throws EntityNotFoundException;
}
