package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.LoginDto;
import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.exceptions.LoginFailedException;

public interface UserService extends CrudService<User, UserDto> {
    UserDto login(LoginDto loginDto) throws LoginFailedException;
}
