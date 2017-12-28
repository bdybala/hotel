package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.Guest;
import org.bdyb.hotel.dto.GuestDto;
import org.bdyb.hotel.service.CrudService;
import org.bdyb.hotel.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guests")
public class GuestController extends AbstractCrudController<Guest, GuestDto> {

    @Autowired
    protected GuestService service;

    public GuestController(CrudService<Guest, GuestDto> service) {
        super(service);
    }
}
