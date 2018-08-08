package org.bdyb.hotel.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.domain.Reservation;
import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.domain.RoomType;
import org.bdyb.hotel.dto.RoomDto;
import org.bdyb.hotel.dto.RoomPaginationResponseDto;
import org.bdyb.hotel.dto.pagination.PaginationDto;
import org.bdyb.hotel.dto.pagination.SearchFieldDto;
import org.bdyb.hotel.dto.pagination.SortFieldDto;
import org.bdyb.hotel.exceptions.badRequest.SearchFieldNotExistingException;
import org.bdyb.hotel.exceptions.badRequest.SortFieldNotExistingException;
import org.bdyb.hotel.mapper.RoomToDtoMapper;
import org.bdyb.hotel.utils.MyStringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RoomDao {

    private final PriceRepository priceRepository;
    private final RoomToDtoMapper roomToDtoMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Room> findAvailableRooms(String roomType, Integer maxCapacity, Date since, Date upTo) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> from = query.from(Room.class);

        // notReservedSubquery
        Subquery<Long> notReservedSubquery = query.subquery(Long.class);
        Root<Room> subRoot = notReservedSubquery.from(Room.class);
        Join<Room, Reservation> join = subRoot.join("reservations", JoinType.LEFT);
        notReservedSubquery.select(subRoot.get("id"));
        notReservedSubquery.where(getReservationSubQuery(cb, join, since, upTo));

        Join<Room, RoomType> joinRoomType = from.join("roomType");
        query.select(from);
        query.where(getSearchAvailabilityPredicates(cb, from, joinRoomType, maxCapacity, roomType, notReservedSubquery));
        List<Room> resultList = entityManager
                .createQuery(query)
                .getResultList();
        int daysBetween = getDaysBetween(since, upTo);
        return resultList.stream()
                .filter(room -> countPrices(room, since, upTo) == daysBetween)
                .collect(Collectors.toList());
    }

    private long countPrices(Room room, Date since, Date upTo) {
        return priceRepository.countAllByRoomAndDayGreaterThanEqualAndDayLessThan(room, since, upTo);
    }

    private int getDaysBetween(Date since, Date upTo) {
        return Days.daysBetween(new DateTime(since).toLocalDate(), new DateTime(upTo).toLocalDate())
                .getDays();
    }

    private Predicate getSearchAvailabilityPredicates(
            CriteriaBuilder cb, Root<Room> from, Join<Room, RoomType> joinRoomType,
            Integer maxCapacity, String roomType, Subquery<Long> subquery) {
        List<Predicate> predicates = new ArrayList<>();
        // roomType
        if (joinRoomType != null) {
            predicates.add(cb.like(joinRoomType.get("name"), roomType));
        }
        // maxCapacity
        if (maxCapacity != null) {
            predicates.add(cb.lessThan(from.get("maxCapacity"), maxCapacity));
        }
        predicates.add(cb.in(from.get("id")).value(subquery));
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private Predicate getReservationSubQuery(CriteriaBuilder cb, Join<Room, Reservation> subRoot, Date since, Date upTo) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isNull(subRoot.get("upTo")));
        predicates.add(cb.lessThanOrEqualTo(subRoot.get("upTo"), since));
        predicates.add(cb.greaterThanOrEqualTo(subRoot.get("since"), upTo));
        return cb.or(predicates.toArray(new Predicate[predicates.size()]));
    }

    @Transactional
    public RoomPaginationResponseDto searchRooms(PaginationDto roomPaginationDto) throws SearchFieldNotExistingException, SortFieldNotExistingException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> from = query.from(Room.class);
        Join<Room, RoomType> roomTypeJoin = from.join("roomType");

        query.select(from);
        if (roomPaginationDto.getSearchFields() != null) {
            try {
                query.where(getSearchPredicates(cb, from, roomTypeJoin, roomPaginationDto.getSearchFields()));
            } catch (IllegalArgumentException e) {
                throw new SearchFieldNotExistingException();
            }
        }
        if (roomPaginationDto.getSortFields() != null) {
            try {
                query.orderBy(getOrderPredicates(cb, from, roomPaginationDto.getSortFields()));
            } catch (IllegalArgumentException e) {
                throw new SortFieldNotExistingException();
            }
        }
        long totalCount = entityManager.createQuery(query).getResultList().size();
        long totalPages = ((totalCount - 1) / roomPaginationDto.getPageSize()) + 1;
        long currentPage = roomPaginationDto.getCurrentPage() > totalPages ? totalPages : roomPaginationDto.getCurrentPage();

        long startingPosition = (currentPage - 1) * roomPaginationDto.getPageSize();

        List<RoomDto> resultList = entityManager.createQuery(query)
                .setFirstResult((int) startingPosition)
                .setMaxResults(roomPaginationDto.getPageSize())
                .getResultList()
                .stream()
                .map(roomToDtoMapper::mapToDto)
                .collect(Collectors.toList());

        return RoomPaginationResponseDto.builder()
                .currentPage(currentPage)
                .totalPages(totalPages)
                .totalRooms(totalCount)
                .rooms(resultList)
                .build();
    }

    private Predicate getSearchPredicates(CriteriaBuilder cb, Root<Room> from, Join<Room, RoomType> joinRoomTypes, List<SearchFieldDto> searchFields) {
        List<Predicate> predicates = new ArrayList<>();
        searchFields.forEach(searchFieldDto -> {
            try {

                if (searchFieldDto.getName().equals("roomTypeName")) {
                    predicates.add(cb.equal(joinRoomTypes.get("name"), searchFieldDto.getValue()));
                } else {
                    predicates.add(
                            cb.like(
                                    cb.upper(from.get(searchFieldDto.getName())),
                                    MyStringUtils.insertPercentageChars(searchFieldDto.getValue()).toUpperCase()
                            )
                    );
                }
            } catch (IllegalArgumentException e) {
                log.error("Wrong attribute: " + searchFieldDto.getName() + " : " + searchFieldDto.getValue());
                throw e;
            }
        });
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private List<Order> getOrderPredicates(CriteriaBuilder cb, Root<Room> from, List<SortFieldDto> sortFields) {
        List<Order> orders = new ArrayList<>();
        sortFields.forEach(sortFieldDto -> {
            switch (sortFieldDto.getSortDirection()) {
                case ASC:
                    orders.add(cb.asc(from.get(sortFieldDto.getName())));
                    break;
                case DESC:
                    orders.add(cb.desc(from.get(sortFieldDto.getName())));
                    break;
            }
        });
        return orders;
    }
}
