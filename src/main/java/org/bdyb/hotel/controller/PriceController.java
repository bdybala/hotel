package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.Price;
import org.bdyb.hotel.dto.PriceDto;
import org.bdyb.hotel.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prices")
public class PriceController extends AbstractCrudController<Price, PriceDto> {

    public PriceController(CrudService<Price, PriceDto> service) {
        super(service);
    }
}
