package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.LoginDto;
import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.exceptions.LoginFailedException;
import org.bdyb.hotel.service.CrudService;
import org.bdyb.hotel.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractCrudController<User, UserDto> {

    public UserController(CrudService<User, UserDto> service) {
        super(service);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserDto login(@RequestBody LoginDto loginDto) throws LoginFailedException {
        return ((UserService) service).login(loginDto);
    }
}
