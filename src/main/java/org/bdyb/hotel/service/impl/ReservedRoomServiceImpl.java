package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.ReservedRoom;
import org.bdyb.hotel.dto.ReservedRoomDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.ReservedRoomMapper;
import org.bdyb.hotel.repository.ReservedRoomRepository;
import org.bdyb.hotel.service.ReservedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ReservedRoomServiceImpl implements ReservedRoomService {

    @Autowired
    private ReservedRoomMapper reservedRoomMapper;
    @Autowired
    private ReservedRoomRepository reservedRoomRepository;

    @Override
    public ReservedRoomDto findOne(Long id) throws EntityNotFoundException {
        return reservedRoomMapper.mapToDto(Optional.ofNullable(reservedRoomRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("ReservedRoom not found with that ID! : " + id)));
    }

    @Override
    public List<ReservedRoomDto> findAll() {
        return reservedRoomMapper.mapToDto(reservedRoomRepository.findAll());
    }

    @Override
    public ReservedRoomDto addOne(ReservedRoomDto dtoToAdd) throws ConflictException {
        return reservedRoomMapper.mapToDto(reservedRoomRepository.save(reservedRoomMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public ReservedRoomDto editOne(ReservedRoomDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        ReservedRoom reservedRoom = Optional.ofNullable(reservedRoomRepository.findOne(dtoToEdit.getId()))
                .orElseThrow(() -> new EntityNotFoundException("ReservedRoom to edit not found! : " + dtoToEdit));
        return reservedRoomMapper.mapToDto(reservedRoomRepository.save(updateReservedRoom(reservedRoom, dtoToEdit)));
    }

    private ReservedRoom updateReservedRoom(ReservedRoom reservedRoom, ReservedRoomDto dtoToEdit) {
        if (dtoToEdit.getSince() != null) {
            reservedRoom.setSince(dtoToEdit.getSince());
        }
        if (dtoToEdit.getUpTo() != null) {
            reservedRoom.setUpTo(dtoToEdit.getUpTo());
        }
        return reservedRoom;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!reservedRoomRepository.exists(id))
            throw new EntityNotFoundException("ReservedRoom to dolete not found with that ID! : " + id);
        reservedRoomRepository.delete(id);
    }

    @Override
    public List<ReservedRoomDto> findByCustomerId(Long customerId) {
        return reservedRoomMapper.mapToDto(reservedRoomRepository.findByCustomerId(customerId));
    }
}
