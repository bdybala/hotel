package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController extends AbstractCrudController<Customer, CustomerDto> {

    public CustomerController(CrudService<Customer, CustomerDto> service) {
        super(service);
    }
}
