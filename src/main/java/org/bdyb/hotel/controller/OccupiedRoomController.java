package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.OccupiedRoom;
import org.bdyb.hotel.dto.OccupiedRoomDto;
import org.bdyb.hotel.service.OccupiedRoomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/occupiedRooms")
public class OccupiedRoomController extends AbstractCrudController<OccupiedRoom, OccupiedRoomDto> {

    public OccupiedRoomController(OccupiedRoomService service) {
        super(service);
    }
}
