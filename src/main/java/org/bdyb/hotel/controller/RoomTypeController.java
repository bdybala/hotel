package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.RoomType;
import org.bdyb.hotel.dto.RoomTypeDto;
import org.bdyb.hotel.service.CrudService;
import org.bdyb.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roomTypes")
public class RoomTypeController extends AbstractCrudController<RoomType, RoomTypeDto> {

    @Autowired
    protected RoomTypeService service;

    public RoomTypeController(CrudService<RoomType, RoomTypeDto> service) {
        super(service);
    }
}
