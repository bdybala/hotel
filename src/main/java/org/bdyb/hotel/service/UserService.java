package org.bdyb.hotel.service;

import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.exceptions.InternalServerErrorException;

import java.util.List;

public interface UserService {

    UserDto findByEmail(String login) throws EntityNotFoundException;
    UserDto findOne(Long id) throws EntityNotFoundException;
    List<UserDto> findAll();

    UserDto save(UserDto newCustomer) throws InternalServerErrorException;

    UserDto edit(UserDto editedCustomer) throws InternalServerErrorException, EntityNotFoundException;

    void delete(Long id) throws InternalServerErrorException, EntityNotFoundException;
}
