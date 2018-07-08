package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.RegisterDto;
import org.bdyb.hotel.dto.UserPaginationResponseDto;
import org.bdyb.hotel.dto.pagination.UserPaginationDto;
import org.bdyb.hotel.exceptions.badRequest.SearchFieldNotExistingException;
import org.bdyb.hotel.exceptions.badRequest.SortFieldNotExistingException;
import org.bdyb.hotel.exceptions.conflict.UserAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoleNotFoundException;
import org.bdyb.hotel.exceptions.notFound.UserIdNotFoundException;

public interface UserService {

    User registerUser (RegisterDto registerDto) throws UserAlreadyExistsConflictException, RoleNotFoundException;
    UserPaginationResponseDto searchUsers(UserPaginationDto userPaginationDto) throws SearchFieldNotExistingException, SortFieldNotExistingException;

    void deleteById(Long id) throws UserIdNotFoundException;
}
