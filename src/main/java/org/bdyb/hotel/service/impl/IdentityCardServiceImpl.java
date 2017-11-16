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

@Primary
@Service
public class IdentityCardServiceImpl implements IdentityCardService {

    @Autowired
    private IdentityCardRepository identityCardRepository;
    @Autowired
    private IdentityCardMapper identityCardMapper;

    @Override
    public IdentityCardDto findOne(Long id) throws EntityNotFoundException {
        return identityCardMapper.mapToDto(findOneEntity(id));
    }

    private IdentityCard findOneEntity(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(identityCardRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Identity card with that id not found! : " + id));
    }

    @Override
    public List<IdentityCardDto> findAll() {
        return identityCardMapper.mapToDto(identityCardRepository.findAll());
    }

    @Override
    public IdentityCardDto addOne(IdentityCardDto dtoToAdd) throws ConflictException {
        if (identityCardRepository.existsByIdCardNumber(dtoToAdd.getIdCardNumber()))
            throw new ConflictException("IdentityCard with that IdCardNumber is already in db! : " + dtoToAdd.getIdCardNumber());
        return identityCardMapper.mapToDto(identityCardRepository.save(identityCardMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public IdentityCardDto editOne(IdentityCardDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        IdentityCard identityCard = findOneEntity(dtoToEdit.getId());
        return identityCardMapper.mapToDto(identityCardRepository.save(updateIdentityCard(identityCard, dtoToEdit)));
    }

    private IdentityCard updateIdentityCard(IdentityCard identityCard, IdentityCardDto dtoToEdit) {
        identityCard.setIdCardType(dtoToEdit.getIdCardType());
        identityCard.setIdCardNumber(dtoToEdit.getIdCardNumber());
        identityCard.setMonthExpiring(dtoToEdit.getMonthExpiring());
        identityCard.setYearExpiring(dtoToEdit.getYearExpiring());
        return identityCard;
    }

    @Override
    public IdentityCardDto delete(Long id) throws EntityNotFoundException {
        IdentityCardDto cardDto = findOne(id);
        identityCardRepository.delete(id);
        return cardDto;
    }
}
