package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.exceptions.InternalServerErrorException;
import org.bdyb.hotel.mapper.CustomerMapper;
import org.bdyb.hotel.mapper.IdentityCardMapper;
import org.bdyb.hotel.repository.CustomerRepository;
import org.bdyb.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private IdentityCardMapper identityCardMapper;


    @Override
    public CustomerDto findOne(Long id) throws EntityNotFoundException {
        return customerMapper.mapToDto(Optional.ofNullable(customerRepository.findOne(id)).orElseThrow(
                () -> new EntityNotFoundException("Customer with that id not found! :" + id)));
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerMapper.mapToDto(customerRepository.findAll());
    }

    @Override
    public CustomerDto save(CustomerDto newCustomer) throws InternalServerErrorException {
        return customerMapper.mapToDto(customerRepository.save(customerMapper.mapToEntity(newCustomer)));
    }

    @Override
    public CustomerDto edit(CustomerDto editedCustomer) throws InternalServerErrorException, EntityNotFoundException {
        Customer customer = customerRepository.findOne(editedCustomer.getId());
        return customerMapper.mapToDto(customerRepository.save(updateCustomer(editedCustomer, customer)));
    }

    private Customer updateCustomer(CustomerDto editedCustomer, Customer customer) {
        if (editedCustomer.getIdentityCardDto() != null) {
            customer.setIdentityCard(identityCardMapper.mapToEntity(editedCustomer.getIdentityCardDto()));
        }
        if (editedCustomer.getName() != null) {
            customer.setName(editedCustomer.getName());
        }
        return customer;
    }

    @Override
    public void delete(Long id) throws InternalServerErrorException, EntityNotFoundException {
        customerRepository.delete(id);
    }
}
