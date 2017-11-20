package org.bdyb.hotel.controller;

import org.bdyb.hotel.dto.ReservationDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ReservationDto> findAll() {
        return reservationService.findAll();
    }

    @RequestMapping(value = "/{id", method = RequestMethod.GET)
    public ReservationDto findOne(@PathVariable("id") Long reservationId) throws EntityNotFoundException {
        return reservationService.findOne(reservationId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ReservationDto addOne(@RequestBody ReservationDto reservationDto) throws ConflictException {
        return reservationService.addOne(reservationDto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ReservationDto editOne(@RequestBody ReservationDto reservationDto) throws ConflictException, EntityNotFoundException {
        return reservationService.editOne(reservationDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ReservationDto delete(@PathVariable("id") Long reservationId) throws EntityNotFoundException {
        return reservationService.delete(reservationId);
    }
}
