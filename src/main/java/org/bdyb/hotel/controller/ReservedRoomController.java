package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.ReservedRoom;
import org.bdyb.hotel.dto.ReservedRoomDto;
import org.bdyb.hotel.service.ReservedRoomService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservedRooms")
public class ReservedRoomController extends AbstractCrudController<ReservedRoom, ReservedRoomDto> {

    public ReservedRoomController(ReservedRoomService service) {
        super(service);
    }

    @RequestMapping(value = "/byCustomer/{id}", method = RequestMethod.GET)
    public List<ReservedRoomDto> findByCustomerId(@PathVariable("id") Long customerId) {
        return ((ReservedRoomService) service).findByCustomerId(customerId);
    }
}
