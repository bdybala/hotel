package org.bdyb.hotel.service;

import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.exceptions.InternalServerErrorException;

import java.util.List;

public interface CustomerService {

    CustomerDto findOne(Long id) throws EntityNotFoundException;
    List<CustomerDto> findAll();

    CustomerDto save(CustomerDto newCustomer) throws InternalServerErrorException;

    CustomerDto edit(CustomerDto editedCustomer) throws InternalServerErrorException, EntityNotFoundException;

    void delete(Long id) throws InternalServerErrorException, EntityNotFoundException;
}
