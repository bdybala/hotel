package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.OccupiedRoom;
import org.bdyb.hotel.dto.OccupiedRoomDto;
import org.bdyb.hotel.service.CrudService;
import org.bdyb.hotel.service.OccupiedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/occupiedCards")
public class OccupiedCardController extends AbstractCrudController<OccupiedRoom, OccupiedRoomDto> {

    @Autowired
    protected OccupiedRoomService service;

    public OccupiedCardController(CrudService<OccupiedRoom, OccupiedRoomDto> service) {
        super(service);
    }
}
