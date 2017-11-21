package org.bdyb.hotel.init;

import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.dto.PriceDto;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.enums.RoomStatus;
import org.bdyb.hotel.enums.RoomType;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.service.PriceService;
import org.bdyb.hotel.service.RoomService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitRoom {

    @Autowired
    private RoomService roomService;
    @Autowired
    private PriceService priceService;

    public void init() {
        RoomDto roomDto = initRoom();
        initPrice(roomDto);
    }


    public RoomDto initRoom() {
        String roomNumber = "1A";
        try {
            RoomDto roomDto = roomService.addOne(RoomDto.builder()
                    .number(roomNumber)
                    .capacity(4)
                    .roomType(RoomType.DORMITORY)
                    .roomStatus(RoomStatus.FREE)
                    .build());
            log.info("INIT Room " + roomNumber + " created");
            return roomDto;
        } catch (ConflictException e) {
            log.info("INIT Room " + roomNumber + " exists");
        }
        return null;
    }

    private void initPrice(RoomDto roomDto) {
        try {
            priceService.addOne(PriceDto.builder()
                    .room(roomDto)
                    .validSince(new DateTime().minusDays(7).toDate())
                    .validUpTo(new DateTime().plusDays(7).toDate())
                    .value(10D)
                    .build());
        } catch (ConflictException ignored) {
        }
    }

}
