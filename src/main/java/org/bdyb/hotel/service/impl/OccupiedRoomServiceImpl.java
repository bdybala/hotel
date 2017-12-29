package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.OccupiedRoom;
import org.bdyb.hotel.dto.OccupiedRoomDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.OccupiedRoomMapper;
import org.bdyb.hotel.repository.OccupiedRoomRepository;
import org.bdyb.hotel.service.OccupiedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class OccupiedRoomServiceImpl implements OccupiedRoomService {

    @Autowired
    private OccupiedRoomRepository occupiedRoomRepository;
    @Autowired
    private OccupiedRoomMapper occupiedRoomMapper;

    @Override
    public OccupiedRoomDto findOne(Long id) throws EntityNotFoundException {
        return occupiedRoomMapper.mapToDto(Optional.ofNullable(occupiedRoomRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("OccupiedRoom not found with that ID! : " + id)));
    }

    @Override
    public List<OccupiedRoomDto> findAll() {
        return occupiedRoomMapper.mapToDto(occupiedRoomRepository.findAll());
    }

    @Override
    public OccupiedRoomDto addOne(OccupiedRoomDto dtoToAdd) throws ConflictException {
        return occupiedRoomMapper.mapToDto(occupiedRoomRepository.save(occupiedRoomMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public OccupiedRoomDto editOne(OccupiedRoomDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        OccupiedRoom occupiedRoom = Optional.ofNullable(occupiedRoomRepository.findOne(dtoToEdit.getId()))
                .orElseThrow(() -> new EntityNotFoundException("OccupiedRoom to edit not found! : " + dtoToEdit));
        return occupiedRoomMapper.mapToDto(occupiedRoomRepository.save(updateOccupiedRoom(occupiedRoom, dtoToEdit)));
    }

    private OccupiedRoom updateOccupiedRoom(OccupiedRoom occupiedRoom, OccupiedRoomDto dtoToEdit) {
        if (dtoToEdit.getSince() != null) {
            occupiedRoom.setSince(dtoToEdit.getSince());
        }
        if (dtoToEdit.getUpTo() != null) {
            occupiedRoom.setUpTo(dtoToEdit.getUpTo());
        }
        return occupiedRoom;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!occupiedRoomRepository.exists(id))
            throw new EntityNotFoundException("OccupiedRoom to delete not found with that ID! : " + id);
        occupiedRoomRepository.delete(id);
    }
}
