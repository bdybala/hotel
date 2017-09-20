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
                .idCardEnum(identityCard.getIdCardEnum())
                .idCardNumber(identityCard.getIdCardNumber())
                .build();
    }

    @Override
    public IdentityCard mapToEntity(IdentityCardDto identityCardDto) {
        return IdentityCard.builder()
                .id(identityCardDto.getId())
                .idCardEnum(identityCardDto.getIdCardEnum())
                .idCardNumber(identityCardDto.getIdCardNumber())
                .build();
    }
}
