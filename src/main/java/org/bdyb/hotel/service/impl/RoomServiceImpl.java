package org.bdyb.hotel.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.config.Constants;
import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.domain.RoomType;
import org.bdyb.hotel.dto.AvailabilityRequestDto;
import org.bdyb.hotel.dto.NewRoomDto;
import org.bdyb.hotel.dto.RoomEditDto;
import org.bdyb.hotel.dto.RoomPaginationResponseDto;
import org.bdyb.hotel.dto.pagination.AvailabilityResponseDto;
import org.bdyb.hotel.dto.pagination.PaginationDto;
import org.bdyb.hotel.exceptions.badRequest.SearchFieldNotExistingException;
import org.bdyb.hotel.exceptions.badRequest.SortFieldNotExistingException;
import org.bdyb.hotel.exceptions.conflict.RoomAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoomIdNotFoundException;
import org.bdyb.hotel.exceptions.notFound.RoomTypeNotFoundException;
import org.bdyb.hotel.mapper.RoomToDtoMapper;
import org.bdyb.hotel.repository.RoomDao;
import org.bdyb.hotel.repository.RoomRepository;
import org.bdyb.hotel.repository.RoomTypeRepository;
import org.bdyb.hotel.service.RoomService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomDao roomDao;
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomToDtoMapper roomToDtoMapper;

    @Override
    public Room createNewRoom(NewRoomDto newRoomDto) throws RoomAlreadyExistsConflictException, RoomTypeNotFoundException {
        if (roomRepository.existsByNumber(newRoomDto.getNumber())) {
            throw new RoomAlreadyExistsConflictException();
        }
        RoomType roomType = roomTypeRepository.findByName(newRoomDto.getRoomTypeName())
                .orElseThrow(RoomTypeNotFoundException::new);
        return roomRepository.save(Room.builder()
                .number(newRoomDto.getNumber())
                .maxCapacity(newRoomDto.getMaxCapacity())
                .roomType(roomType)
                .build());
    }

    @Override
    public AvailabilityResponseDto findAvailableRooms(AvailabilityRequestDto availabilityRequestDto) throws RoomTypeNotFoundException {
        // todo remove roomType
        if (!roomTypeRepository.existsByName(availabilityRequestDto.getRoomTypeName()))
            throw new RoomTypeNotFoundException();
        if (availabilityRequestDto.getCurrentPage() == null) availabilityRequestDto.setCurrentPage(1);
        if (availabilityRequestDto.getPageSize() == null)
            availabilityRequestDto.setPageSize(Constants.DEFAULT_PAGE_SIZE);
        List<Room> availableRooms = roomDao.findAvailableRooms(availabilityRequestDto);
        return AvailabilityResponseDto.builder()
            .availableRooms(roomToDtoMapper.mapToDto(availableRooms)).build();
    }

    @Override
    public RoomPaginationResponseDto searchRooms(PaginationDto roomPaginationDto) throws SearchFieldNotExistingException, SortFieldNotExistingException {
        if (roomPaginationDto.getCurrentPage() == null) roomPaginationDto.setCurrentPage(1);
        if (roomPaginationDto.getPageSize() == null) roomPaginationDto.setPageSize(Constants.DEFAULT_PAGE_SIZE);
        return roomDao.searchRooms(roomPaginationDto);
    }

    @Override
    public void deleteById(Long id) throws RoomIdNotFoundException {
        Room room = roomRepository.findById(id).orElseThrow(RoomIdNotFoundException::new);
        roomRepository.delete(room);
    }

    @Override
    public Room editRoom(RoomEditDto roomEditDto) throws RoomIdNotFoundException, RoomAlreadyExistsConflictException, RoomTypeNotFoundException {
        Room room = roomRepository.findById(roomEditDto.getId())
                .orElseThrow(RoomIdNotFoundException::new);

        Optional<Long> idByRoomNumber = roomRepository
                .findByNumber(roomEditDto.getNumber())
                .map(Room::getId);
        if (idByRoomNumber.isPresent() && !idByRoomNumber.get().equals(roomEditDto.getId())) {
            throw new RoomAlreadyExistsConflictException();
        }

        room.setNumber(roomEditDto.getNumber());
        room.setMaxCapacity(roomEditDto.getMaxCapacity());

        if (roomTypeNameHasBeenEdited(roomEditDto, room)) {
            RoomType roomType = roomTypeRepository.findByName(roomEditDto.getRoomTypeName())
                    .orElseThrow(RoomTypeNotFoundException::new);
            room.setRoomType(roomType);
        }
        roomRepository.save(room);
        return room;
    }

    private boolean roomTypeNameHasBeenEdited(RoomEditDto roomEditDto, Room room) {
        return !room.getRoomType().getName().equals(roomEditDto.getRoomTypeName());
    }
}
