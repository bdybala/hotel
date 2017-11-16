package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements EntityMapper<Customer, CustomerDto> {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IdentityCardMapper identityCardMapper;

    @Override
    public CustomerDto mapToDto(Customer customer) {
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        if (customer.getIdentityCard() != null)
            customerDto.setIdentityCard(identityCardMapper.mapToDto(customer.getIdentityCard()));
        return customerDto;
    }

    @Override
    public Customer mapToEntity(CustomerDto customerDto) {
        if (customerDto.getId() != null) {
            Customer customer = customerRepository.findOne(customerDto.getId());
            if (customer != null) return customer;
        }
        Customer customer = modelMapper.map(customerDto, Customer.class);
        if (customerDto.getIdentityCard() != null)
            customer.setIdentityCard(identityCardMapper.mapToEntity(customerDto.getIdentityCard()));
        return customer;
    }
}
