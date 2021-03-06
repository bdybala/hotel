package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.IdentityCard;
import org.bdyb.hotel.dto.IdentityCardDto;
import org.bdyb.hotel.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/identityCards")
public class IdentityCardController extends AbstractCrudController<IdentityCard, IdentityCardDto> {

    public IdentityCardController(CrudService<IdentityCard, IdentityCardDto> service) {
        super(service);
    }
}
