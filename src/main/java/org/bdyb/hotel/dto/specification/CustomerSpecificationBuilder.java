package org.bdyb.hotel.dto.specification;

import org.bdyb.hotel.domain.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecificationBuilder {

    private final List<SearchCriteria> params;

    public CustomerSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public CustomerSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Customer> build() {
        if (params.size() == 0) return null;

        List<Specification<Customer>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new CustomerSpecification(param));
        }

        Specification<Customer> result = specs.get(0);
        for (int i = 0; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }

}
