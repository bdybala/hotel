package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.Reservation;
import org.bdyb.hotel.dto.ReservationDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.ReservationMapper;
import org.bdyb.hotel.repository.CustomerRepository;
import org.bdyb.hotel.repository.ReservationRepository;
import org.bdyb.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ReservationDto findOne(Long id) throws EntityNotFoundException {
        return reservationMapper.mapToDto(Optional.ofNullable(reservationRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with that ID! : " + id)));
    }

    @Override
    public List<ReservationDto> findAll() {
        return reservationMapper.mapToDto(reservationRepository.findAll());
    }

    @Override
    public ReservationDto addOne(ReservationDto dtoToAdd) throws ConflictException {
        return reservationMapper.mapToDto(reservationRepository.save(reservationMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public ReservationDto editOne(ReservationDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        Reservation reservation = Optional.ofNullable(reservationRepository.findOne(dtoToEdit.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Reservation to edit not found! : " + dtoToEdit));
        return reservationMapper.mapToDto(reservationRepository.save(updateReservation(reservation, dtoToEdit)));
    }

    private Reservation updateReservation(Reservation reservation, ReservationDto dtoToEdit) {
        if (dtoToEdit.getCustomer() != null) {
            reservation.setCustomer(customerRepository.findOne(dtoToEdit.getCustomer().getId()));
        }
        return reservation;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!reservationRepository.exists(id))
            throw new EntityNotFoundException("Reservation to delete not found with that ID! : " + id);
        reservationRepository.delete(id);
    }
}
