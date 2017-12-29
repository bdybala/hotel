package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.RoomType;
import org.bdyb.hotel.dto.RoomTypeDto;
import org.bdyb.hotel.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roomTypes")
public class RoomTypeController extends AbstractCrudController<RoomType, RoomTypeDto> {

    public RoomTypeController(CrudService<RoomType, RoomTypeDto> service) {
        super(service);
    }
}
