package org.bdyb.hotel.init;

import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.dto.PriceDto;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.enums.RoomType;
import org.bdyb.hotel.exceptions.ConflictException;
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
    }


    public RoomDto initRoom() {
        String roomNumber = "10";
        for (int i = 0; i < 3; i++) {
            try {
                RoomDto roomDto = roomService.addOne(RoomDto.builder()
                        .number(roomNumber + i)
                        .capacity(4)
                        .roomType(RoomType.DORMITORY)
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
                    .validSince(new DateTime().minusDays(7).toDate())
                    .validUpTo(new DateTime().plusDays(7).toDate())
                    .value(10D)
                    .build());
        } catch (ConflictException ignored) {
        }
    }

}
