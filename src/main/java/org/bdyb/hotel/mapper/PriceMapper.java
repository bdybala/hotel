package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Price;
import org.bdyb.hotel.dto.PriceDto;
import org.bdyb.hotel.repository.PriceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PriceMapper implements EntityMapper<Price, PriceDto> {

    @Autowired
    private PriceRepository priceRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public PriceDto mapToDto(Price price) {
        return modelMapper.map(price, PriceDto.class);
    }

    @Override
    public Price mapToEntity(PriceDto priceDto) {
        if (priceDto.getId() != null) {
            Optional<Price> price = Optional.ofNullable(priceRepository.findOne(priceDto.getId()));
            if (price.isPresent()) return price.get();
        }
        return modelMapper.map(priceDto, Price.class);
    }
}
