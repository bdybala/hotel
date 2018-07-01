package org.bdyb.hotel.services;


import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.NewRoomDto;
import org.bdyb.hotel.exceptions.conflict.RoomAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoomTypeNotFoundException;
import org.bdyb.hotel.repository.RoomRepository;
import org.bdyb.hotel.repository.RoomTypeRepository;
import org.bdyb.hotel.service.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class RoomIntegrationTest {

    private static final String FIRST_ROOM_NUMBER = "1A";
    private static final String SECOND_ROOM_NUMBER = "2A";

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Test(expected = RoomAlreadyExistsConflictException.class)
    public void createNewRoomNegativeNumberExists() throws RoomTypeNotFoundException, RoomAlreadyExistsConflictException {
        // given
        roomRepository.save(prepareRoom());

        // when
        roomService.createNewRoom(prepareNewRoomDto(FIRST_ROOM_NUMBER, true));

        //then
    }

    @Test(expected = RoomTypeNotFoundException.class)
    public void createNewRoomNegativeRoomTypeNotFound() throws RoomTypeNotFoundException, RoomAlreadyExistsConflictException {
        // given

        // when
        roomService.createNewRoom(prepareNewRoomDto(SECOND_ROOM_NUMBER, false));

        //then
    }

    @Test
    public void createNewRoomPositive() {
        // given

        // when

        //then
    }

    private Room prepareRoom() {
        Room room = new Room();
        room.setRoomType(roomTypeRepository.findAll().get(0));
        room.setNumber(FIRST_ROOM_NUMBER);
        room.setMaxCapacity(5);
        return room;
    }

    private NewRoomDto prepareNewRoomDto(String number, boolean roomTypeExisting) {
        NewRoomDto room = new NewRoomDto();
        room.setNumber(number);
        room.setMaxCapacity(5);
        room.setRoomTypeName(roomTypeExisting ? "QUAD" : "elo");
        return room;
    }
}
