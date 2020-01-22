package org.bdyb.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.dto.NewVisitDto;
import org.bdyb.hotel.exceptions.notFound.ReservationNotFoundException;
import org.bdyb.hotel.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/visit")
public class VisitController {

  private final VisitService visitService;

  @PostMapping
  public ResponseEntity addVisit(@RequestBody NewVisitDto newVisitDto)
      throws ReservationNotFoundException {
    visitService.addVisit(newVisitDto);
    return new ResponseEntity(HttpStatus.OK);
  }

}
