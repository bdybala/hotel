package org.bdyb.hotel.controller;

import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.exceptions.InternalServerErrorException;
import org.bdyb.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CustomerDto findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
        return customerService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerDto> findAll() {
        return customerService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public CustomerDto addOneCustomer(@RequestBody CustomerDto newCustomer) throws ConflictException {
        return customerService.addOne(newCustomer);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CustomerDto editOneCustomer(@RequestBody CustomerDto editedCustomer) throws EntityNotFoundException, ConflictException {
        return customerService.editOne(editedCustomer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable Long id) throws InternalServerErrorException, EntityNotFoundException {
        customerService.delete(id);
    }


}
