package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements EntityMapper<Customer, CustomerDto> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public CustomerDto mapToDto(Customer customer) {
        return mapper.map(customer, CustomerDto.class);
    }

    @Override
    public Customer mapToEntity(CustomerDto customerDto) {
        return mapper.map(customerDto, Customer.class);
    }
}
