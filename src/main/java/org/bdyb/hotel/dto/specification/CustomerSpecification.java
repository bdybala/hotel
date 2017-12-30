package org.bdyb.hotel.dto.specification;

import lombok.AllArgsConstructor;
import org.bdyb.hotel.domain.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

@AllArgsConstructor
public class CustomerSpecification implements Specification<Customer> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        ArrayList<Predicate> predicates = new ArrayList<>();
        if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return cb.like(cb.upper(root.<String>get(criteria.getKey())), "%" + criteria.getValue().toString().toUpperCase() + "%");
            } else {
                return cb.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}