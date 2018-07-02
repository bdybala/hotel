package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Reservation;
import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.domain.RoomType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class RoomDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Room> findAvailableRooms(String roomType, Integer maxCapacity, Date since, Date upTo) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> from = query.from(Room.class);

        // subquery
        Subquery<Long> subquery = query.subquery(Long.class);
        Root<Room> subRoot = subquery.from(Room.class);
        Join<Room, Reservation> join = subRoot.join("reservations", JoinType.LEFT);
        subquery.select(subRoot.get("id"));
        subquery.where(getReservationSubQuery(cb, join, since, upTo));
        //

        Join<Room, RoomType> joinRoomType = from.join("roomType");
        query.select(from);
        query.where(getSearchPredicates(cb, from, joinRoomType, maxCapacity, roomType, subquery));
        return entityManager
                .createQuery(query)
                .getResultList();
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
        predicates.add(cb.isTrue(from.get("isFree")));
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
