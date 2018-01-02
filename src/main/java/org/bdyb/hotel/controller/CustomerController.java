package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.Customer;
import org.bdyb.hotel.dto.CustomerDto;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController extends AbstractCrudController<Customer, CustomerDto> {

    public CustomerController(CustomerService service) {
        super(service);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<CustomerDto> findAll(@RequestParam(value = "search", required = false) String search) {
        if (search != null) {
            return ((CustomerService) service).findAll(search);
        } else {
            return service.findAll();
        }
    }

    @RequestMapping(value = "/byIdCard/{number}", method = RequestMethod.GET)
    public CustomerDto findByIdCardNumber(@PathVariable("number") String number) throws EntityNotFoundException {
        return ((CustomerService) service).findByIdentityCardNumber(number);
    }
}
