package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;

import java.util.List;

public interface CustomerService extends CrudService<Customer, CustomerDto> {
    List<CustomerDto> findAll(String search);
}
