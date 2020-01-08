package org.bdyb.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.dto.NewReservationDto;
import org.bdyb.hotel.exceptions.notFound.EntityNotFoundException;
import org.bdyb.hotel.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
  public ResponseEntity addReservation(@RequestBody NewReservationDto reservationDto)
      throws EntityNotFoundException {
    reservationService.addReservation(reservationDto);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity getAll() {
    return new ResponseEntity<>(reservationService.getAll(), HttpStatus.OK);
  }
}
