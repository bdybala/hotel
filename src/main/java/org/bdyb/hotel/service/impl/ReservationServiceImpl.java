package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.Reservation;
import org.bdyb.hotel.dto.ReservationDto;
import org.bdyb.hotel.dto.RoomStatusDto;
import org.bdyb.hotel.enums.RoomStatusEnum;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.ReservationMapper;
import org.bdyb.hotel.repository.ReservationRepository;
import org.bdyb.hotel.service.ReservationService;
import org.bdyb.hotel.service.RoomStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private RoomStatusService roomStatusService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public ReservationDto findOne(Long id) throws EntityNotFoundException {
        return reservationMapper.mapToDto(findOneEntity(id));
    }

    private Reservation findOneEntity(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(reservationRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with that ID : " + id));
    }

    @Override
    public List<ReservationDto> findAll() {
        return reservationMapper.mapToDto(reservationRepository.findAll());
    }

    @Override
    @Transactional
    public ReservationDto addOne(ReservationDto dtoToAdd) throws ConflictException {
        ReservationDto reservationDto = reservationMapper.mapToDto(reservationRepository.save(reservationMapper.mapToEntity(dtoToAdd)));
        roomStatusService.addOne(RoomStatusDto.builder()
                .name(RoomStatusEnum.RESERVED)
                .since(dtoToAdd.getSince())
                .upTo(dtoToAdd.getUpTo())
                .room(dtoToAdd.getRoom())
                .build());
        return reservationDto;
    }

    @Override
    public ReservationDto editOne(ReservationDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        return reservationMapper.mapToDto(reservationRepository.save(reservationMapper.mapToEntity(dtoToEdit)));
    }

    @Override
    public ReservationDto delete(Long id) throws EntityNotFoundException {
        ReservationDto reservationDto = findOne(id);
        reservationRepository.delete(id);
        return reservationDto;
    }
}
