package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Price;
import org.bdyb.hotel.dto.PriceDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper implements EntityMapper<Price, PriceDto> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public PriceDto mapToDto(Price price) {
        return mapper.map(price, PriceDto.class);
    }

    @Override
    public Price mapToEntity(PriceDto priceDto) {
        return mapper.map(priceDto, Price.class);
    }
}
