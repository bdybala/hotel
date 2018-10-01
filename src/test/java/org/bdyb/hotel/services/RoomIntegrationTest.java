package org.bdyb.hotel.services;


import org.bdyb.hotel.domain.Price;
import org.bdyb.hotel.domain.Reservation;
import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.domain.RoomType;
import org.bdyb.hotel.dto.AvailabilityRequestDto;
import org.bdyb.hotel.dto.NewRoomDto;
import org.bdyb.hotel.dto.RoomPaginationResponseDto;
import org.bdyb.hotel.dto.pagination.AvailabilityResponseDto;
import org.bdyb.hotel.dto.pagination.PaginationDto;
import org.bdyb.hotel.dto.pagination.SearchFieldDto;
import org.bdyb.hotel.dto.pagination.SortFieldDto;
import org.bdyb.hotel.exceptions.badRequest.SearchFieldNotExistingException;
import org.bdyb.hotel.exceptions.badRequest.SortFieldNotExistingException;
import org.bdyb.hotel.exceptions.conflict.RoomAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoomTypeNotFoundException;
import org.bdyb.hotel.repository.PriceRepository;
import org.bdyb.hotel.repository.ReservationRepository;
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
import org.springframework.data.domain.Sort;
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
    @Autowired
    private ReservationRepository reservationRepository;

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
        priceRepository.saveAll(preparePricesForRoom(savedRoom, TestUtils.TOMORROW, TestUtils.IN_THREE_DAYS));

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
        List<Price> savedPrices = priceRepository.saveAll(preparePricesForRoom(savedRoom, TestUtils.TOMORROW, TestUtils.IN_TWO_DAYS));

        // when
        AvailabilityResponseDto availableRooms = roomService.findAvailableRooms(
                prepareAvailabilityRequest(savedRoom.getRoomType().getName(), TestUtils.TOMORROW, TestUtils.IN_THREE_DAYS));

        // then
        Assert.assertNotNull(availableRooms.getAvailableRooms());
        Assert.assertEquals(0, availableRooms.getAvailableRooms().size());
    }

    @Test
    public void findAvailableRoomsTestPositiveAllFree() throws RoomTypeNotFoundException {
        // given
        Room savedRoom = roomRepository.save(TestUtils.getRoomBuilder()
                .withRoomNumber(FIRST_ROOM_NUMBER)
                .withRoomType(roomTypeRepository.findAll().get(0))
                .withMaxCapacity(5)
                .build());
        List<Price> savedPrices = priceRepository.saveAll(preparePricesForRoom(savedRoom, TestUtils.TOMORROW, TestUtils.IN_THREE_DAYS));

        // when
        AvailabilityResponseDto availableRooms = roomService.findAvailableRooms(
                prepareAvailabilityRequest(savedRoom.getRoomType().getName(), TestUtils.TOMORROW, TestUtils.IN_TWO_DAYS));

        // then
        Assert.assertNotNull(availableRooms.getAvailableRooms());
        Assert.assertEquals(1, availableRooms.getAvailableRooms().size());
    }

    @Test
    public void findAvailableRoomsPositiveReservedFiltered() throws RoomTypeNotFoundException {
        // given
        RoomType roomType = roomTypeRepository.findAll().get(0);
        Room freeRoom = roomRepository.save(TestUtils.getRoomBuilder()
                .withRoomNumber(FIRST_ROOM_NUMBER)
                .withMaxCapacity(5)
                .withRoomType(roomType)
                .build());
        Room reservedRoom = roomRepository.save(TestUtils.getRoomBuilder()
                .withRoomNumber(SECOND_ROOM_NUMBER)
                .withMaxCapacity(5)
                .withRoomType(roomType)
                .build());
        List<Price> firstRoomPrices = priceRepository.saveAll(preparePricesForRoom(freeRoom, TestUtils.THREE_DAYS_AGO, TestUtils.IN_FOUR_DAYS));
        List<Price> secondroomPrices = priceRepository.saveAll(preparePricesForRoom(reservedRoom, TestUtils.THREE_DAYS_AGO, TestUtils.IN_FOUR_DAYS));
        Reservation reservation = prepareReservation(reservedRoom, TestUtils.IN_THREE_DAYS, TestUtils.IN_FOUR_DAYS);

        // when
        AvailabilityResponseDto availableRoomsInTwoDays = roomService.findAvailableRooms(
                prepareAvailabilityRequest(roomType.getName(), TestUtils.IN_AN_HOUR, TestUtils.IN_TWO_DAYS));
        AvailabilityResponseDto availableRoomsInFourDays = roomService.findAvailableRooms(
                prepareAvailabilityRequest(roomType.getName(), TestUtils.IN_AN_HOUR, TestUtils.IN_FOUR_DAYS));

        // then
        Assert.assertEquals(2, availableRoomsInTwoDays.getAvailableRooms().size());
        Assert.assertEquals(1, availableRoomsInFourDays.getAvailableRooms().size());
    }

    private Reservation prepareReservation(Room room, Date since, Date upTo) {
        return reservationRepository.save(Reservation.builder()
                .since(since)
                .upTo(upTo)
                .firstName("FIRSTNAME")
                .lastName("LASTNAME")
                .email("EMAIL")
                .phoneNumber("514514514")
                .room(room)
                .build());
    }

    @Test(expected = SearchFieldNotExistingException.class)
    public void searchRoomsNegativeNotExistingSearchField() throws SearchFieldNotExistingException, SortFieldNotExistingException {
        // given

        // when
        roomService.searchRooms(prepareSearchPagination(
                getSearchFieldsNotExisting(),
                getSortFieldsNotExisting(),
                1));

        // then
    }

    @Test(expected = SortFieldNotExistingException.class)
    public void searchRoomsNegativeNotExistingSortField() throws SearchFieldNotExistingException, SortFieldNotExistingException {
        // given

        // when
        roomService.searchRooms(prepareSearchPagination(
                getSearchFieldsOk(),
                getSortFieldsNotExisting(),
                1));

        // then
    }

    @Test
    public void searchRoomsPositiveTwoPages() throws SearchFieldNotExistingException, SortFieldNotExistingException {
        // given
        roomRepository.deleteAll();
        int roomsQuantity = 11;
        prepareRoomEntity(roomsQuantity);

        // when
        RoomPaginationResponseDto roomPaginationResponseDto = roomService.searchRooms(prepareSearchPagination(
                getSearchFieldsOk(),
                getSortFieldsOk(),
                2
        ));

        // then
        Assert.assertNotNull(roomPaginationResponseDto);
        Assert.assertEquals(roomsQuantity - 10, roomPaginationResponseDto.getRooms().size());
    }

    private void prepareRoomEntity(int roomsQuantity) {
        RoomType roomType = roomTypeRepository.findAll().get(0);
        for (int i = 0; i < roomsQuantity; i++) {
            roomRepository.save(
                    Room.builder()
                            .number(FIRST_ROOM_NUMBER + i)
                            .maxCapacity(5)
                            .roomType(roomType)
                            .build()
            );
        }
    }

    private List<Price> preparePricesForRoom(Room savedRoom, Date since, Date upTo) {
        List<Price> prices = new ArrayList<>();
        DateTime start = new DateTime(since).withTimeAtStartOfDay();
        DateTime end = new DateTime(upTo).plusDays(1).withTimeAtStartOfDay();
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

    private PaginationDto prepareSearchPagination(List<SearchFieldDto> searchFields, List<SortFieldDto> sortFields, int page) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setCurrentPage(page);
        paginationDto.setSearchFields(searchFields);
        paginationDto.setSortFields(sortFields);
        return paginationDto;
    }

    private List<SearchFieldDto> getSearchFieldsNotExisting() {
        List<SearchFieldDto> searchFields = new ArrayList<>();
        searchFields.add(new SearchFieldDto("roomName", "number"));
        return searchFields;
    }

    private List<SearchFieldDto> getSearchFieldsOk() {
        List<SearchFieldDto> searchFields = new ArrayList<>();
        searchFields.add(new SearchFieldDto("number", FIRST_ROOM_NUMBER));
        return searchFields;
    }

    private List<SortFieldDto> getSortFieldsNotExisting() {
        List<SortFieldDto> sortFields = new ArrayList<>();
        sortFields.add(new SortFieldDto("roomName", Sort.Direction.ASC));
        return sortFields;
    }

    private List<SortFieldDto> getSortFieldsOk() {
        List<SortFieldDto> sortFields = new ArrayList<>();
        sortFields.add(new SortFieldDto("number", Sort.Direction.ASC));
        return sortFields;
    }
}
