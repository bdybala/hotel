package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.service.CrudService;
import org.bdyb.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController extends AbstractCrudController<Customer, CustomerDto> {

    @Autowired
    protected CustomerService service;

    public CustomerController(CrudService<Customer, CustomerDto> service) {
        super(service);
    }
}
