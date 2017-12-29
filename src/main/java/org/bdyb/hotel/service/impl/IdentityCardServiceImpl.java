package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.IdentityCard;
import org.bdyb.hotel.dto.IdentityCardDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.IdentityCardMapper;
import org.bdyb.hotel.repository.IdentityCardRepository;
import org.bdyb.hotel.service.IdentityCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class IdentityCardServiceImpl implements IdentityCardService {

    @Autowired
    private IdentityCardRepository identityCardRepository;
    @Autowired
    private IdentityCardMapper identityCardMapper;

    @Override
    public IdentityCardDto findOne(Long id) throws EntityNotFoundException {
        return identityCardMapper.mapToDto(Optional.ofNullable(identityCardRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("IdentityCard not found with that ID! : " + id)));
    }

    @Override
    public List<IdentityCardDto> findAll() {
        return identityCardMapper.mapToDto(identityCardRepository.findAll());
    }

    @Override
    public IdentityCardDto addOne(IdentityCardDto dtoToAdd) throws ConflictException {
        return identityCardMapper.mapToDto(identityCardRepository.save(identityCardMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public IdentityCardDto editOne(IdentityCardDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        IdentityCard identityCard = Optional.ofNullable(identityCardRepository.findOne(dtoToEdit.getId()))
                .orElseThrow(() -> new EntityNotFoundException("IdentityCard to edit not found! : " + dtoToEdit));
        return identityCardMapper.mapToDto(identityCardRepository.save(updateIdentityCard(identityCard, dtoToEdit)));
    }

    private IdentityCard updateIdentityCard(IdentityCard identityCard, IdentityCardDto dtoToEdit) {
        if (dtoToEdit.getNumber() != null) {
            identityCard.setNumber(dtoToEdit.getNumber());
        }
        if (dtoToEdit.getExpiringDate() != null) {
            identityCard.setExpiringDate(dtoToEdit.getExpiringDate());
        }
        return identityCard;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!identityCardRepository.exists(id))
            throw new EntityNotFoundException("IdentityCard to delete not found with that ID! : " + id);
        identityCardRepository.delete(id);
    }
}
