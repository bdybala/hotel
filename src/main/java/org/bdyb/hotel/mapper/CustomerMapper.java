package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements EntityMapper<Customer, CustomerDto> {

    @Override
    public CustomerDto mapToDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .build();
    }

    @Override
    public Customer mapToEntity(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .build();
    }
}
