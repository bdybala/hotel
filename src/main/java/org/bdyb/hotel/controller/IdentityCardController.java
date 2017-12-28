package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.IdentityCard;
import org.bdyb.hotel.dto.IdentityCardDto;
import org.bdyb.hotel.service.CrudService;
import org.bdyb.hotel.service.IdentityCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/identityCards")
public class IdentityCardController extends AbstractCrudController<IdentityCard, IdentityCardDto> {

    @Autowired
    protected IdentityCardService service;

    public IdentityCardController(CrudService<IdentityCard, IdentityCardDto> service) {
        super(service);
    }
}
