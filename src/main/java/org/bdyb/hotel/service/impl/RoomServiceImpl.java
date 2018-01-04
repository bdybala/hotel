package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.DateRange;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.dto.RoomWithTotalPriceDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.RoomMapper;
import org.bdyb.hotel.repository.RoomRepository;
import org.bdyb.hotel.repository.RoomTypeRepository;
import org.bdyb.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Override
    public RoomDto findOne(Long id) throws EntityNotFoundException {
        return roomMapper.mapToDto(Optional.ofNullable(roomRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Room not found with that ID! : " + id)));
    }

    @Override
    public List<RoomDto> findAll() {
        return roomMapper.mapToDto(roomRepository.findAll());
    }

    @Override
    public RoomDto addOne(RoomDto dtoToAdd) throws ConflictException {
        if (roomRepository.existsByNumber(dtoToAdd.getNumber()))
            throw new ConflictException("Room with that number already exists! : " + dtoToAdd);
        return roomMapper.mapToDto(roomRepository.save(roomMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public RoomDto editOne(RoomDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        Room room = Optional.ofNullable(roomRepository.findOne(dtoToEdit.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Room to edit not found! : " + dtoToEdit));
        return roomMapper.mapToDto(roomRepository.save(updateRoom(room, dtoToEdit)));
    }

    private Room updateRoom(Room room, RoomDto dtoToEdit) {
        if (dtoToEdit.getMaxCapacity() != null) {
            room.setMaxCapacity(dtoToEdit.getMaxCapacity());
        }
        if (dtoToEdit.getNumber() != null) {
            room.setNumber(dtoToEdit.getNumber());
        }
        if (dtoToEdit.getRoomType() != null) {
            room.setRoomType(roomTypeRepository.findOne(dtoToEdit.getRoomType().getId()));
        }
        return room;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!roomRepository.exists(id))
            throw new EntityNotFoundException("Room to delete not found with that ID! : " + id);
        roomRepository.delete(id);
    }

    @Override
    public List<RoomDto> findAllFree(DateRange dateRange) {
        return roomMapper.mapToDto(roomRepository.findAllFree(dateRange.getSince(), dateRange.getUpTo()));
    }

    @Override
    public List<RoomWithTotalPriceDto> findFreeByRoomType(Date since, Date to, String roomType) {
        String[] rooms = roomRepository.findRooms(roomType, since, to);
        List<RoomWithTotalPriceDto> freeRooms = new ArrayList<>();

        Arrays.asList(rooms).forEach(
                s -> {
                    String[] room = s.split(",");
                    freeRooms.add(RoomWithTotalPriceDto.builder()
                            .roomId(Long.valueOf(room[0]))
                            .maxCapacity(Integer.valueOf(room[1]))
                            .roomNumber(room[2])
                            .roomType(room[3])
                            .totalPrice(Double.valueOf(room[4]))
                            .build());
                }
        );

        return freeRooms;
    }
}
