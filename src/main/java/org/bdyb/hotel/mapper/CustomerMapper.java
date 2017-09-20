package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements EntityMapper<Customer, CustomerDto> {

    @Autowired
    private IdentityCardMapper identityCardMapper;

    @Override
    public CustomerDto mapToDto(Customer customer) {
        CustomerDto customerDto = CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .identityCardDto(identityCardMapper.mapToDto(customer.getIdentityCard()))
                .build();
        return customerDto;
    }

    @Override
    public Customer mapToEntity(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .build();
    }
}
