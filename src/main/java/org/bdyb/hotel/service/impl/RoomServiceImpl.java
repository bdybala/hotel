package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.dto.DateRangeDto;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.RoomMapper;
import org.bdyb.hotel.repository.RoomRepository;
import org.bdyb.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMapper roomMapper;


    @Override
    public RoomDto findOne(Long id) throws EntityNotFoundException {
        return roomMapper.mapToDto(findOneEntity(id));
    }

    private Room findOneEntity(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(roomRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Customer with that id not found! : " + id));
    }

    @Override
    public List<RoomDto> findAll() {
        return roomMapper.mapToDto(roomRepository.findAll());
    }

    @Override
    public RoomDto addOne(RoomDto dtoToAdd) throws ConflictException {
        if (roomRepository.existsByNumber(dtoToAdd.getNumber()))
            throw new ConflictException("Room with that number already exists! : " + dtoToAdd.getNumber());
        return roomMapper.mapToDto(roomRepository.save(roomMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public RoomDto editOne(RoomDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        Room room = findOneEntity(dtoToEdit.getId());
        return roomMapper.mapToDto(roomRepository.save(updateRoom(room, dtoToEdit)));
    }

    private Room updateRoom(Room room, RoomDto dtoToEdit) {
        room.setCapacity(dtoToEdit.getCapacity());
        room.setNumber(dtoToEdit.getNumber());
        room.setRoomType(dtoToEdit.getRoomType());
        return room;
    }

    @Override
    public RoomDto delete(Long id) throws EntityNotFoundException {
        RoomDto roomDto = findOne(id);
        roomRepository.delete(id);
        return roomDto;
    }

    @Override
    public List<RoomDto> findFreeInInterval(DateRangeDto dateRangeDto) {
        return roomMapper.mapToDto(roomRepository.findFreeInInterval(dateRangeDto.getFrom(), dateRangeDto.getTo()));
    }
}
