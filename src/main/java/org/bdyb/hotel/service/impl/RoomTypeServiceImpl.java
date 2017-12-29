package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.RoomType;
import org.bdyb.hotel.dto.RoomTypeDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.RoomTypeMapper;
import org.bdyb.hotel.repository.RoomTypeRepository;
import org.bdyb.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Override
    public RoomTypeDto findOne(Long id) throws EntityNotFoundException {
        return roomTypeMapper.mapToDto(Optional.ofNullable(roomTypeRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("RoomType not found with that ID! : " + id)));
    }

    @Override
    public List<RoomTypeDto> findAll() {
        return roomTypeMapper.mapToDto(roomTypeRepository.findAll());
    }

    @Override
    public RoomTypeDto addOne(RoomTypeDto dtoToAdd) throws ConflictException {
        if (roomTypeRepository.existsByName(dtoToAdd.getName()))
            throw new ConflictException("RoomType already exists with that name! : " + dtoToAdd);
        return roomTypeMapper.mapToDto(roomTypeRepository.save(roomTypeMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public RoomTypeDto editOne(RoomTypeDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        RoomType roomType = Optional.ofNullable(roomTypeRepository.findOne(dtoToEdit.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Room to edit not found! : " + dtoToEdit));
        return roomTypeMapper.mapToDto(roomTypeRepository.save(updateRoomType(roomType, dtoToEdit)));
    }

    private RoomType updateRoomType(RoomType roomType, RoomTypeDto dtoToEdit) {
        if (dtoToEdit.getName() != null) {
            roomType.setName(dtoToEdit.getName());
        }
        if (dtoToEdit.getDescription() != null) {
            roomType.setDescription(dtoToEdit.getDescription());
        }
        return roomType;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!roomTypeRepository.exists(id))
            throw new EntityNotFoundException("RoomType to delete not found with that ID! : " + id);
        roomTypeRepository.delete(id);
    }
}
