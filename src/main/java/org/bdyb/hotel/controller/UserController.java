package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractCrudController<User, UserDto> {

    public UserController(CrudService<User, UserDto> service) {
        super(service);
    }
}
