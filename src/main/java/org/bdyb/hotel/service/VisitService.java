package org.bdyb.hotel.service;

import org.bdyb.hotel.dto.NewVisitDto;
import org.bdyb.hotel.exceptions.notFound.ReservationNotFoundException;

public interface VisitService {

  void addVisit(NewVisitDto newVisitDto) throws ReservationNotFoundException;
}
