package org.bdyb.hotel.init;

import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.domain.RoomType;
import org.bdyb.hotel.dto.PriceDto;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.service.PriceService;
import org.bdyb.hotel.service.RoomService;
import org.bdyb.hotel.service.RoomTypeService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitRooms {

    @Autowired
    private RoomService roomService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private RoomTypeService roomTypeService;

    public void init() {
        RoomDto roomDto = initRoom();
    }


    private RoomDto initRoom() {
        String roomNumber = "10";
        for (int i = 0; i < 3; i++) {
            try {
                RoomDto roomDto = roomService.addOne(RoomDto.builder()
                        .number(roomNumber + i)
                        .maxCapacity(4)
                        .roomType(roomTypeService.findAll().get(i))
                        .build());
                log.info("INIT Room " + roomNumber + " created");
                initPrice(roomDto);
            } catch (ConflictException e) {
                log.info("INIT Room " + roomNumber + " exists");
            }
        }
        return null;
    }

    private void initPrice(RoomDto roomDto) {
        try {
            priceService.addOne(PriceDto.builder()
                    .room(roomDto)
                    .since(new DateTime().minusDays(7).toDate())
                    .upTo(new DateTime().plusDays(7).toDate())
                    .value(10D)
                    .build());
        } catch (ConflictException ignored) {
        }
    }
}
