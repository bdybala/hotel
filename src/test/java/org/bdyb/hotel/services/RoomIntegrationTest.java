package org.bdyb.hotel.services;


import org.bdyb.hotel.domain.Price;
import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.AvailabilityRequestDto;
import org.bdyb.hotel.dto.NewRoomDto;
import org.bdyb.hotel.exceptions.conflict.RoomAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoomTypeNotFoundException;
import org.bdyb.hotel.repository.PriceRepository;
import org.bdyb.hotel.repository.RoomRepository;
import org.bdyb.hotel.repository.RoomTypeRepository;
import org.bdyb.hotel.service.RoomService;
import org.bdyb.hotel.utils.TestUtils;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class RoomIntegrationTest {

    private static final String FIRST_ROOM_NUMBER = "1A";
    private static final String SECOND_ROOM_NUMBER = "2A";
    private static final Double PRICE = 10D;

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private PriceRepository priceRepository;

    @Test(expected = RoomAlreadyExistsConflictException.class)
    public void createNewRoomNegativeNumberExists() throws RoomTypeNotFoundException, RoomAlreadyExistsConflictException {
        // given
        roomRepository.save(TestUtils.getRoomBuilder()
                .withRoomNumber(FIRST_ROOM_NUMBER)
                .withRoomType(roomTypeRepository.findAll().get(0))
                .withMaxCapacity(5)
                .build());

        // when
        roomService.createNewRoom(prepareNewRoomDto(FIRST_ROOM_NUMBER, true));

        // then
    }

    @Test(expected = RoomTypeNotFoundException.class)
    public void createNewRoomNegativeRoomTypeNotFound() throws RoomTypeNotFoundException, RoomAlreadyExistsConflictException {
        // given

        // when
        roomService.createNewRoom(prepareNewRoomDto(SECOND_ROOM_NUMBER, false));

        // then
    }

    @Test
    public void createNewRoomPositive() throws RoomTypeNotFoundException, RoomAlreadyExistsConflictException {
        // given

        // when
        Room newRoom = roomService.createNewRoom(prepareNewRoomDto(SECOND_ROOM_NUMBER, true));

        // then
        Assert.assertNotNull(newRoom);
        Assert.assertEquals(1, roomRepository.count());
    }

    @Test(expected = RoomTypeNotFoundException.class)
    public void findAvailableRoomsTestNegativeRoomTypeNotFound() throws RoomTypeNotFoundException {
        // given
        Room savedRoom = roomRepository.save(TestUtils.getRoomBuilder()
                .withRoomNumber(FIRST_ROOM_NUMBER)
                .withRoomType(roomTypeRepository.findAll().get(0))
                .withMaxCapacity(5)
                .build());
        priceRepository.save(preparePricesForRoom(savedRoom, TestUtils.TOMORROW, TestUtils.IN_THREE_DAYS));

        // when
        roomService.findAvailableRooms(
                prepareAvailabilityRequest(null, TestUtils.TOMORROW, TestUtils.IN_TWO_DAYS));

        // then
    }

    @Test
    public void findAvailableRoomsTestNegativePriceNotFound() throws RoomTypeNotFoundException {
        // given
        Room savedRoom = roomRepository.save(TestUtils.getRoomBuilder()
                .withRoomNumber(FIRST_ROOM_NUMBER)
                .withRoomType(roomTypeRepository.findAll().get(0))
                .withMaxCapacity(5)
                .build());
        List<Price> savedPrices = priceRepository.save(preparePricesForRoom(savedRoom, TestUtils.TOMORROW, TestUtils.IN_TWO_DAYS));

        // when
        List<Room> availableRooms = roomService.findAvailableRooms(
                prepareAvailabilityRequest(savedRoom.getRoomType().getName(), TestUtils.TOMORROW, TestUtils.IN_THREE_DAYS));

        // then
        Assert.assertNotNull(availableRooms);
        Assert.assertEquals(0, availableRooms.size());
    }

    @Test
    public void findAvailableRoomsTestPositiveAllFree() throws RoomTypeNotFoundException {
        // given
        Room savedRoom = roomRepository.save(TestUtils.getRoomBuilder()
                .withRoomNumber(FIRST_ROOM_NUMBER)
                .withRoomType(roomTypeRepository.findAll().get(0))
                .withMaxCapacity(5)
                .build());
        List<Price> savedPrices = priceRepository.save(preparePricesForRoom(savedRoom, TestUtils.TOMORROW, TestUtils.IN_THREE_DAYS));

        // when
        List<Room> availableRooms = roomService.findAvailableRooms(
                prepareAvailabilityRequest(savedRoom.getRoomType().getName(), TestUtils.TOMORROW, TestUtils.IN_TWO_DAYS));

        // then
        Assert.assertNotNull(availableRooms);
        Assert.assertEquals(1, availableRooms.size());
    }

    private List<Price> preparePricesForRoom(Room savedRoom, Date since, Date upTo) {
        List<Price> prices = new ArrayList<>();
        DateTime start = new DateTime(since).withTimeAtStartOfDay();
        DateTime end = new DateTime(upTo).withTimeAtStartOfDay();
        for (DateTime i = start; i.isBefore(end); i = i.plusDays(1)) {
            Price price = Price.builder()
                    .value(PRICE)
                    .day(i.toDate())
                    .room(savedRoom)
                    .build();
            prices.add(price);
        }
        return prices;
    }

    private AvailabilityRequestDto prepareAvailabilityRequest(String roomTypeName, Date since, Date upTo) {
        AvailabilityRequestDto availabilityRequestDto = new AvailabilityRequestDto();
        availabilityRequestDto.setSince(since);
        availabilityRequestDto.setUpTo(upTo);
        availabilityRequestDto.setRoomTypeName(roomTypeName);
        return availabilityRequestDto;
    }

    private NewRoomDto prepareNewRoomDto(String number, boolean roomTypeExisting) {
        NewRoomDto room = new NewRoomDto();
        room.setNumber(number);
        room.setMaxCapacity(5);
        room.setRoomTypeName(roomTypeExisting ? "QUAD" : "elo");
        return room;
    }
}
