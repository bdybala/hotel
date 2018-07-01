package org.bdyb.hotel.repository;

import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.pagination.SearchFieldDto;
import org.bdyb.hotel.dto.pagination.SortFieldDto;
import org.bdyb.hotel.dto.pagination.UserPaginationDto;
import org.bdyb.hotel.exceptions.badRequest.SearchFieldNotExistingException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public List<User> findUsers(UserPaginationDto userPaginationDto) throws SearchFieldNotExistingException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> from = query.from(User.class);

        query.select(from);
        if (userPaginationDto.getSearchFields() != null) {
            try {
                query.where(getSearchPredicates(cb, from, userPaginationDto.getSearchFields()));
            } catch (IllegalArgumentException e) {
                throw new SearchFieldNotExistingException();
            }
        }

        if (userPaginationDto.getSortFields() != null)
            query.orderBy(getOrderPredicates(cb, from, userPaginationDto.getSortFields()));

        return entityManager.createQuery(query)
                .getResultList();

    }

    private Predicate getSearchPredicates(CriteriaBuilder cb, Root<User> from, List<SearchFieldDto> searchFields)
            throws IllegalArgumentException {
        List<Predicate> predicates = new ArrayList<>();
        searchFields.forEach(searchFieldDto -> {
            try {
                predicates.add(cb.like(from.get(searchFieldDto.getName()), insertPercentageChars(searchFieldDto.getValue())));
            } catch (IllegalArgumentException e) {
                log.error("Wrong attribute: " + searchFieldDto.getName());
                throw e;
            }
        });
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private String insertPercentageChars(String value) {
        if (!value.startsWith("%")) {
            value = "%" + value;
        }
        if (!value.endsWith("%")) {
            value = value + "%";
        }
        return value;
    }

    private List<Order> getOrderPredicates(CriteriaBuilder cb, Root<User> from, List<SortFieldDto> sortFields) {
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
