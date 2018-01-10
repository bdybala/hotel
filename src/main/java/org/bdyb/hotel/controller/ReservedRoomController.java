package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.ReservedRoom;
import org.bdyb.hotel.dto.ReservedRoomDto;
import org.bdyb.hotel.service.ReservedRoomService;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/betweenTwoDates", method = RequestMethod.GET)
    public List<ReservedRoomDto> findBetweenTwoDates(
            @RequestParam("since") Long sinceEpoch,
            @RequestParam("to") Long toEpoch) {
        return ((ReservedRoomService) service).findBetweenTwoDates(sinceEpoch, toEpoch);
    }
}
