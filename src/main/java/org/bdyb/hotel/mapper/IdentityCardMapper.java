package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.IdentityCard;
import org.bdyb.hotel.dto.IdentityCardDto;
import org.springframework.stereotype.Component;

@Component
public class IdentityCardMapper implements EntityMapper<IdentityCard, IdentityCardDto> {
    @Override
    public IdentityCardDto mapToDto(IdentityCard identityCard) {
        return IdentityCardDto.builder()
                .id(identityCard.getId())
                .idCardType(identityCard.getIdCardType())
                .idCardNumber(identityCard.getIdCardNumber())
                .build();
    }

    @Override
    public IdentityCard mapToEntity(IdentityCardDto identityCardDto) {
        return IdentityCard.builder()
                .id(identityCardDto.getId())
                .idCardType(identityCardDto.getIdCardType())
                .idCardNumber(identityCardDto.getIdCardNumber())
                .build();
    }
}
