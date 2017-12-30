package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.Price;
import org.bdyb.hotel.dto.PriceDto;
import org.bdyb.hotel.dto.RoomIdAndDay;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.PriceMapper;
import org.bdyb.hotel.repository.PriceRepository;
import org.bdyb.hotel.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private PriceMapper priceMapper;

    @Override
    public PriceDto findOne(Long id) throws EntityNotFoundException {
        return priceMapper.mapToDto(Optional.ofNullable(priceRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Price not found with that ID! : " + id)));
    }

    @Override
    public List<PriceDto> findAll() {
        return priceMapper.mapToDto(priceRepository.findAll());
    }

    @Override
    public PriceDto addOne(PriceDto dtoToAdd) throws ConflictException {
        // TODO conflictException when price for Room exists in Date Interval
        return priceMapper.mapToDto(priceRepository.save(priceMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public PriceDto editOne(PriceDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        Price price = Optional.ofNullable(priceRepository.findOne(dtoToEdit.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Price to edit not found! :" + dtoToEdit));
        return priceMapper.mapToDto(priceRepository.save(updatePrice(price, dtoToEdit)));
    }

    private Price updatePrice(Price price, PriceDto dtoToEdit) {
        if (dtoToEdit.getValue() != null) {
            price.setValue(dtoToEdit.getValue());
        }
        if (dtoToEdit.getSince() != null) {
            price.setSince(dtoToEdit.getSince());
        }
        if (dtoToEdit.getUpTo() != null) {
            price.setUpTo(dtoToEdit.getUpTo());
        }
        return price;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!priceRepository.exists(id))
            throw new EntityNotFoundException("Price to delete not found with that ID! : " + id);
    }

    @Override
    public PriceDto findByRoomIdAndDay(RoomIdAndDay roomIdAndDay) throws EntityNotFoundException {
        return priceMapper.mapToDto(Optional.ofNullable(priceRepository.findByRoomIdAndForDay(roomIdAndDay.getRoomId(), roomIdAndDay.getDay()))
                .orElseThrow(() -> new EntityNotFoundException("Price for that roomId and Day not found! : " + roomIdAndDay)));
    }

    @Override
    public List<PriceDto> findByRoomId(Long roomId) {
        return priceMapper.mapToDto(priceRepository.findByRoomId(roomId));
    }
}
