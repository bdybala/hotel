package org.bdyb.hotel.service;

import java.util.List;
import org.bdyb.hotel.dto.NewVisitDto;
import org.bdyb.hotel.dto.VisitDto;
import org.bdyb.hotel.exceptions.notFound.ReservationNotFoundException;

public interface VisitService {

  void addVisit(NewVisitDto newVisitDto) throws ReservationNotFoundException;

  List<VisitDto> getAll();
}
