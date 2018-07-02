package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.domain.RoomType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class RoomDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Room> findAvailableRooms(RoomType roomType, Integer maxCapacity, Date since, Date upTo) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> from = query.from(Room.class);

        query.select(from);
        if (roomType != null) {
            query.where(getSearchPredicates(cb, from, maxCapacity));
        }
        return entityManager
                .createQuery(query)
                .getResultList();
    }

    private Predicate getSearchPredicates(CriteriaBuilder cb, Root<Room> from, Integer maxCapacity) {
        List<Predicate> predicates = new ArrayList<>();
        // roomType
        // maxCapacity
        if (maxCapacity != null) {
            predicates.add(cb.lessThan(from.get("maxCapacity"), maxCapacity));
        }
        predicates.add(cb.isTrue(from.get("isFree")));
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));

    }
}
