package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.Price;
import org.bdyb.hotel.dto.PriceDto;
import org.bdyb.hotel.dto.RoomIdAndDay;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.PriceMapper;
import org.bdyb.hotel.repository.PriceRepository;
import org.bdyb.hotel.repository.RoomRepository;
import org.bdyb.hotel.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Primary
@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public PriceDto findOne(Long id) throws EntityNotFoundException {
        return priceMapper.mapToDto(findOneEntity(id));
    }

    private Price findOneEntity(Long id) throws EntityNotFoundException  {
        return Optional.ofNullable(priceRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Price with that id not found in db! : " + id));
    }

    @Override
    public List<PriceDto> findAll() {
        return priceMapper.mapToDto(priceRepository.findAll());
    }

    @Override
    public PriceDto addOne(PriceDto dtoToAdd) throws ConflictException {
        return priceMapper.mapToDto(priceRepository.save(priceMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public PriceDto editOne(PriceDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        Price price = Optional.ofNullable(priceRepository.findOne(dtoToEdit.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Price to edit not found with that ID : " + dtoToEdit.getId()));
        return priceMapper.mapToDto(priceRepository.save(updatePrice(price, dtoToEdit)));
    }

    private Price updatePrice(Price price, PriceDto dtoToEdit) {
        price.setValue(dtoToEdit.getValue());
        price.setValidSince(dtoToEdit.getValidSince());
        price.setValidUpTo(dtoToEdit.getValidUpTo());
        return price;
    }

    @Override
    public PriceDto delete(Long id) throws EntityNotFoundException {
        PriceDto priceDto = findOne(id);
        priceRepository.delete(id);
        return priceDto;
    }

    @Override
    public List<PriceDto> findByRoomId(Long roomId) throws EntityNotFoundException {
        return priceMapper.mapToDto(priceRepository.findByRoomId(roomId));
    }

    @Override
    public PriceDto findByRoomIdAndForDay(RoomIdAndDay roomIdAndDay) throws EntityNotFoundException {
        return priceMapper.mapToDto(Optional.ofNullable(priceRepository.findByRoomIdAndForDay(roomIdAndDay.getRoomId(), roomIdAndDay.getDay()))
                .orElseThrow(() -> new EntityNotFoundException("Price for that day and room not found! : " + roomIdAndDay)));
    }
}
