package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.IdentityCard;
import org.bdyb.hotel.dto.IdentityCardDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IdentityCardMapper implements EntityMapper<IdentityCard, IdentityCardDto> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public IdentityCardDto mapToDto(IdentityCard identityCard) {
        return mapper.map(identityCard, IdentityCardDto.class);
    }

    @Override
    public IdentityCard mapToEntity(IdentityCardDto identityCardDto) {
        return mapper.map(identityCardDto, IdentityCard.class);
    }
}
