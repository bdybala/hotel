package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.Guest;
import org.bdyb.hotel.dto.GuestDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.GuestMapper;
import org.bdyb.hotel.repository.GuestRepository;
import org.bdyb.hotel.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private GuestMapper guestMapper;

    @Override
    public GuestDto findOne(Long id) throws EntityNotFoundException {
        return guestMapper.mapToDto(Optional.ofNullable(guestRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Guest with that id not found! : " + id)));
    }

    @Override
    public List<GuestDto> findAll() {
        return guestMapper.mapToDto(guestRepository.findAll());
    }

    @Override
    public GuestDto addOne(GuestDto dtoToAdd) throws ConflictException {
        return guestMapper.mapToDto(guestRepository.save(guestMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public GuestDto editOne(GuestDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        Guest guest = Optional.ofNullable(guestRepository.findOne(dtoToEdit.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Guest to edit not found in db! : " + dtoToEdit));
        return guestMapper.mapToDto(guestRepository.save(updateGuest(guest, dtoToEdit)));
    }

    private Guest updateGuest(Guest guest, GuestDto dtoToEdit) {
        if (dtoToEdit.getSince() != null) {
            guest.setSince(dtoToEdit.getSince());
        }
        if (dtoToEdit.getUpTo() != null) {
            guest.setUpTo(dtoToEdit.getUpTo());
        }
        // TODO update occupied rooms
        return guest;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!guestRepository.exists(id))
            throw new EntityNotFoundException("Guest to delete with that id not found! : " + id);
        guestRepository.delete(id);
    }
}
