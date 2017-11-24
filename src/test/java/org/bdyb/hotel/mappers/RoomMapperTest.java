package org.bdyb.hotel.mappers;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.enums.RoomType;
import org.bdyb.hotel.mapper.RoomMapper;
import org.bdyb.hotel.repository.RoomRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class RoomMapperTest {

    @Mock
    private RoomRepository roomRepository;

    @Spy
    @InjectMocks
    private RoomMapper roomMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapToDtoOk() {

        Room room = prepareRoom();
        RoomDto roomDto = roomMapper.mapToDto(room);

        Assert.assertEquals(room.getId(), roomDto.getId());
        Assert.assertEquals(room.getNumber(), roomDto.getNumber());
        Assert.assertEquals(room.getCapacity(), roomDto.getCapacity());
        Assert.assertEquals(room.getRoomType(), roomDto.getRoomType());
    }

    private Room prepareRoom() {
        return Room.builder()
                .id(1L)
                .number("1a")
                .capacity(3)
                .roomType(RoomType.DOUBLE_ROOM)
                .build();
    }

    @Test
    public void testMapToEntityNewOk() {
        RoomDto roomDto = prepareRoomDto();
        Mockito.when(roomRepository.findOne(Mockito.anyLong())).thenReturn(null);
        Room room = roomMapper.mapToEntity(roomDto);

        Assert.assertEquals(roomDto.getId(), room.getId());
        Assert.assertEquals(roomDto.getNumber(), room.getNumber());
        Assert.assertEquals(roomDto.getCapacity(), room.getCapacity());
        Assert.assertEquals(roomDto.getRoomType(), room.getRoomType());
    }

    private RoomDto prepareRoomDto() {
        return RoomDto.builder()
                .id(1L)
                .number("1a")
                .capacity(3)
                .roomType(RoomType.DOUBLE_ROOM)
                .build();
    }

    @Test
    public void testMapToEntityExistingOk() {
        RoomDto roomDto = prepareRoomDto();
        Mockito.when(roomRepository.findOne(Mockito.anyLong())).thenReturn(prepareRoom());

        Room room = roomMapper.mapToEntity(roomDto);

        Assert.assertEquals(roomDto.getId(), room.getId());
        Assert.assertEquals(roomDto.getNumber(), room.getNumber());
        Assert.assertEquals(roomDto.getCapacity(), room.getCapacity());
        Assert.assertEquals(roomDto.getRoomType(), room.getRoomType());
    }
}
