package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.LoginDto;
import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.exceptions.LoginFailedException;
import org.bdyb.hotel.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractCrudController<User, UserDto> {

    public UserController(UserService service) {
        super(service);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserDto login(@RequestBody LoginDto loginDto) throws LoginFailedException {
        return ((UserService) service).login(loginDto);
    }

    @RequestMapping(value = "/byUsernameAndRole", method = RequestMethod.GET)
    public List<UserDto> findAllFreeByRoomType(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "roleName", required = false) String roleName) {
        return ((UserService) service).findByUsernameAndRoleName(username, roleName);
    }
}
