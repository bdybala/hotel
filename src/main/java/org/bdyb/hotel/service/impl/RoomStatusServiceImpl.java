package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.RoomStatus;
import org.bdyb.hotel.dto.RoomIdAndDateDto;
import org.bdyb.hotel.dto.RoomStatusDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.RoomStatusMapper;
import org.bdyb.hotel.repository.RoomStatusRepository;
import org.bdyb.hotel.service.RoomStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Primary
@Service
public class RoomStatusServiceImpl implements RoomStatusService {

    @Autowired
    private RoomStatusRepository roomStatusRepository;

    @Autowired
    private RoomStatusMapper roomStatusMapper;

    @Override
    public RoomStatusDto findOne(Long id) throws EntityNotFoundException {
        return roomStatusMapper.mapToDto(findOneEntity(id));
    }

    private RoomStatus findOneEntity(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(roomStatusRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("RoomStatus with that id not found! : " + id));
    }

    @Override
    public List<RoomStatusDto> findAll() {
        return roomStatusMapper.mapToDto(roomStatusRepository.findAll());
    }

    @Override
    public RoomStatusDto addOne(RoomStatusDto dtoToAdd) throws ConflictException {
        return roomStatusMapper.mapToDto(roomStatusRepository.save(roomStatusMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public RoomStatusDto editOne(RoomStatusDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        RoomStatus roomStatus = findOneEntity(dtoToEdit.getId());
        return roomStatusMapper.mapToDto(roomStatusRepository.save(updateRoomStatus(dtoToEdit, roomStatus)));
    }

    private RoomStatus updateRoomStatus(RoomStatusDto dtoToEdit, RoomStatus roomStatus) {
        roomStatus.setName(dtoToEdit.getName());
        roomStatus.setSince(dtoToEdit.getSince());
        roomStatus.setUpTo(dtoToEdit.getUpTo());
        return roomStatus;
    }

    @Override
    public RoomStatusDto delete(Long id) throws EntityNotFoundException {
        RoomStatusDto roomStatusDto = findOne(id);
        roomStatusRepository.delete(id);
        return roomStatusDto;
    }

    @Override
    public List<RoomStatusDto> findByRoomId(Long roomId) {
        return roomStatusMapper.mapToDto(roomStatusRepository.findByRoomId(roomId));
    }

    @Override
    public RoomStatusDto findByRoomIdAndDate(RoomIdAndDateDto roomIdAndDateDto) {
        return roomStatusMapper.mapToDto(roomStatusRepository.findByRoomIdAndDate(roomIdAndDateDto.getRoomId(),roomIdAndDateDto.getDate()));
    }
}
