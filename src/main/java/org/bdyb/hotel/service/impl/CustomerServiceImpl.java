package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.IdentityCardMapper;
import org.bdyb.hotel.mapper.CustomerMapper;
import org.bdyb.hotel.repository.IdentityCardRepository;
import org.bdyb.hotel.repository.CustomerRepository;
import org.bdyb.hotel.service.IdentityCardService;
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
    private IdentityCardRepository identityCardRepository;

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private IdentityCardMapper identityCardMapper;

    @Autowired
    private IdentityCardService identityCardService;

    @Override
    public CustomerDto findOne(Long id) throws EntityNotFoundException {
        return customerMapper.mapToDto(findOneEntity(id));
    }

    private Customer findOneEntity(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(customerRepository.findOne(id)).orElseThrow(
                () -> new EntityNotFoundException("Customer with that id not found! : " + id));
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerMapper.mapToDto(customerRepository.findAll());
    }

    @Override
    public CustomerDto addOne(CustomerDto dtoToAdd) throws ConflictException {
        if (identityCardRepository.existsByIdCardNumber(dtoToAdd.getIdentityCard().getIdCardNumber()))
            throw new ConflictException("Customer with that IdCardNumber is already in db! : " + dtoToAdd.getIdentityCard().getIdCardNumber());
        return customerMapper.mapToDto(customerRepository.save(customerMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public CustomerDto editOne(CustomerDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        Customer customer = findOneEntity(dtoToEdit.getId());
        return customerMapper.mapToDto(customerRepository.save(updateUser(dtoToEdit, customer)));
    }

    private Customer updateUser(CustomerDto editedCustomer, Customer customer) {
        if (editedCustomer.getIdentityCard() != null) {
            customer.setIdentityCard(identityCardMapper.mapToEntity(editedCustomer.getIdentityCard()));
        }
        if (editedCustomer.getName() != null) {
            customer.setName(editedCustomer.getName());
        }
        if (editedCustomer.getSurname() != null) {
            customer.setSurname(editedCustomer.getSurname());
        }
        return customer;
    }

    @Override
    public CustomerDto delete(Long id) throws EntityNotFoundException {
        CustomerDto customerDto = findOne(id);
        customerRepository.delete(id);
        return customerDto;
    }

}
