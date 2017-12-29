package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.Reservation;
import org.bdyb.hotel.dto.ReservationDto;
import org.bdyb.hotel.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController extends AbstractCrudController<Reservation, ReservationDto> {

    public ReservationController(CrudService<Reservation, ReservationDto> service) {
        super(service);
    }
}
