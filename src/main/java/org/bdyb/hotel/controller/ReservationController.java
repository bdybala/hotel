package org.bdyb.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.dto.ReservationDto;
import org.bdyb.hotel.exceptions.notFound.EntityNotFoundException;
import org.bdyb.hotel.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {

  private final ReservationService reservationService;

  @PostMapping
  public ResponseEntity addReservation(@RequestBody ReservationDto reservationDto)
      throws EntityNotFoundException {
    reservationService.addReservation(reservationDto);
    return new ResponseEntity(HttpStatus.OK);
  }
}
