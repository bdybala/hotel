package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.RoomMapper;
import org.bdyb.hotel.repository.RoomRepository;
import org.bdyb.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMapper roomMapper;


    @Override
    public RoomDto findOne(Long id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<RoomDto> findAll() {
        return null;
    }

    @Override
    public RoomDto addOne(RoomDto dtoToAdd) throws ConflictException {
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
