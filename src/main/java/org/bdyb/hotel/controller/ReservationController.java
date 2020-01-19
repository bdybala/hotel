package org.bdyb.hotel.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.dto.NewReservationDto;
import org.bdyb.hotel.dto.ReservationDto;
import org.bdyb.hotel.exceptions.notFound.EntityNotFoundException;
import org.bdyb.hotel.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public ResponseEntity<List<ReservationDto>> getAllByEmail(
      @RequestParam(required = false) String email) {
    return new ResponseEntity<>(
        StringUtils.isEmpty(email) ? reservationService.getAll()
            : reservationService.getAllByEmail(email), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<ReservationDto> editReservation(@RequestBody ReservationDto reservationDto)
      throws EntityNotFoundException {
    return new ResponseEntity<>(reservationService.editReservation(reservationDto), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity removeReservation(@PathVariable("id") Long id) {
    reservationService.removeReservation(id);
    return new ResponseEntity(HttpStatus.OK);
  }
}
