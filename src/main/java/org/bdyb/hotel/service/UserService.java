package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.LoginRequestDto;
import org.bdyb.hotel.dto.LoginResponseDto;
import org.bdyb.hotel.dto.RegisterDto;
import org.bdyb.hotel.dto.UserEditDto;
import org.bdyb.hotel.dto.UserPaginationResponseDto;
import org.bdyb.hotel.dto.pagination.PaginationDto;
import org.bdyb.hotel.exceptions.LoginFailedException;
import org.bdyb.hotel.exceptions.badRequest.SearchFieldNotExistingException;
import org.bdyb.hotel.exceptions.badRequest.SortFieldNotExistingException;
import org.bdyb.hotel.exceptions.conflict.UserAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoleNotFoundException;
import org.bdyb.hotel.exceptions.notFound.UserIdNotFoundException;

public interface UserService {

  User registerUser(RegisterDto registerDto)
      throws UserAlreadyExistsConflictException, RoleNotFoundException;

  UserPaginationResponseDto searchUsers(PaginationDto userPaginationDto)
      throws SearchFieldNotExistingException, SortFieldNotExistingException;

  void deleteById(Long id) throws UserIdNotFoundException;

  User editUser(UserEditDto userDto)
      throws UserIdNotFoundException, UserAlreadyExistsConflictException, RoleNotFoundException;

  LoginResponseDto login(LoginRequestDto loginRequestDto) throws LoginFailedException;
}
