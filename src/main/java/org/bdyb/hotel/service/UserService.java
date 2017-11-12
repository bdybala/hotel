package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.RegisterNewUserDto;
import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;

public interface UserService extends CrudService<User, UserDto> {

    UserDto findByEmail(String login) throws EntityNotFoundException;

    UserDto register(RegisterNewUserDto registerNewUserDto) throws ConflictException;
}
