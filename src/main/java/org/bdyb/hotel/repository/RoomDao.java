package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Reservation;
import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.domain.RoomType;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoomDao {

    @Autowired
    private PriceRepository priceRepository;

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
        query.where(getSearchPredicates(cb, from, joinRoomType, maxCapacity, roomType, notReservedSubquery));
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

    private Predicate getSearchPredicates(
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
}
