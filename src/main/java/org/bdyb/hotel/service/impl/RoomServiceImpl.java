package org.bdyb.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.domain.RoomType;
import org.bdyb.hotel.dto.NewRoomDto;
import org.bdyb.hotel.exceptions.conflict.RoomAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoomTypeNotFoundException;
import org.bdyb.hotel.repository.RoomRepository;
import org.bdyb.hotel.repository.RoomTypeRepository;
import org.bdyb.hotel.service.RoomService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;

    @Override
    public Room createNewRoom(NewRoomDto newRoomDto) throws RoomAlreadyExistsConflictException, RoomTypeNotFoundException {
        if (roomRepository.existsByNumber(newRoomDto.getNumber())) {
            throw new RoomAlreadyExistsConflictException();
        }
        RoomType roomType = roomTypeRepository.findByName(newRoomDto.getRoomTypeName())
                .orElseThrow(RoomTypeNotFoundException::new);
        return roomRepository.save(Room.builder()
                .number(newRoomDto.getNumber())
                .maxCapacity(newRoomDto.getMaxCapacity())
                .roomType(roomType)
                .build());
    }
}
