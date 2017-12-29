package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.CustomerMapper;
import org.bdyb.hotel.repository.CustomerRepository;
import org.bdyb.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerDto findOne(Long id) throws EntityNotFoundException {
        return customerMapper.mapToDto(Optional.ofNullable(customerRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Customer with that id not found! : " + id)));
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerMapper.mapToDto(customerRepository.findAll());
    }

    @Override
    @Transactional
    public CustomerDto addOne(CustomerDto dtoToAdd) throws ConflictException {
        if (dtoToAdd.getPesel() != null && customerRepository.existsByPesel(dtoToAdd.getPesel()))
            throw new ConflictException("Customer with that PESEL already exists : " + dtoToAdd);
        return customerMapper.mapToDto(customerRepository.save(customerMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public CustomerDto editOne(CustomerDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        Customer customer = Optional.ofNullable(customerRepository.findOne(dtoToEdit.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Customer to edit not found with that ID: " + dtoToEdit));
        return customerMapper.mapToDto(
                customerRepository.save(updateCustomer(customer, dtoToEdit))
        );
    }

    private Customer updateCustomer(Customer customer, CustomerDto dtoToEdit) {
        if (dtoToEdit.getFirstName() != null) {
            customer.setFirstName(dtoToEdit.getFirstName());
        }
        if (dtoToEdit.getLastName() != null) {
            customer.setLastName(dtoToEdit.getLastName());
        }
        if (dtoToEdit.getPesel() != null) {
            customer.setPesel(dtoToEdit.getPesel());
        }
        if (dtoToEdit.getBirthday() != null) {
            customer.setBirthday(dtoToEdit.getBirthday());
        }
        return customer;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!customerRepository.exists(id))
            throw new EntityNotFoundException("Customer with that id not found! : " + id);
        customerRepository.delete(id);
    }
}
