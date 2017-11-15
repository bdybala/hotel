package org.bdyb.hotel.service.impl;

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
        return roomMapper.mapToDto(Optional.ofNullable(roomRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("User with that id not found! : " + id)));
    }

    @Override
    public List<RoomDto> findAll() {
        return roomMapper.mapToDto(roomRepository.findAll());
    }

    @Override
    public RoomDto addOne(RoomDto dtoToAdd) throws ConflictException {
        try {
            return roomMapper.mapToDto(roomRepository.save(roomMapper.mapToEntity(dtoToAdd)));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause().toString());
            System.out.println(e.getCause().getClass());
        }
        return null;
    }

    @Override
    public RoomDto editOne(RoomDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        return null;
    }

    @Override
    public RoomDto delete(Long id) throws EntityNotFoundException {
        return null;
    }
}
