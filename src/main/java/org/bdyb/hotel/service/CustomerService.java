package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.exceptions.EntityNotFoundException;

public interface CustomerService extends CrudService<Customer, CustomerDto> {

}
