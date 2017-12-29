package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements EntityMapper<Customer, CustomerDto> {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    IdentityCardMapper identityCardMapper;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public CustomerDto mapToDto(Customer customer) {
        return mapper.map(customer, CustomerDto.class);
    }

    @Override
    public Customer mapToEntity(CustomerDto customerDto) {
        if (customerDto.getId() != null) {
            Customer customer = customerRepository.findOne(customerDto.getId());
            if (customer != null) return customer;
        }
        Customer customer = mapper.map(customerDto, Customer.class);
        if (customerDto.getIdentityCard() != null)
            customer.setIdentityCard(identityCardMapper.mapToEntity(customerDto.getIdentityCard()));
        return customer;
    }
}
