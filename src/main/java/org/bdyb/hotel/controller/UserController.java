package org.bdyb.hotel.controller;

import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.exceptions.InternalServerErrorException;
import org.bdyb.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/logged")
    public UserDto getLoggedUser(Principal principal) throws EntityNotFoundException {
        if (principal != null) {
            return userService.findByEmail(principal.getName());
        } else {
            throw new EntityNotFoundException("There is no logged user!");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
        return userService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserDto createCustomer(@RequestBody UserDto newCustomer) throws InternalServerErrorException {
        return userService.save(newCustomer);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UserDto editCustomer(@RequestBody UserDto editedCustomer) throws InternalServerErrorException, EntityNotFoundException {
        return userService.edit(editedCustomer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable Long id) throws InternalServerErrorException, EntityNotFoundException {
        userService.delete(id);
    }

}
