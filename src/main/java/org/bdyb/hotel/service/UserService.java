package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.RegisterDto;
import org.bdyb.hotel.exceptions.conflict.UserAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoleNotFoundException;

public interface UserService {

    User registerUser (RegisterDto registerDto) throws UserAlreadyExistsConflictException, RoleNotFoundException;
}
