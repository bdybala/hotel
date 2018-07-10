package org.bdyb.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.dto.RegisterDto;
import org.bdyb.hotel.dto.UserPaginationResponseDto;
import org.bdyb.hotel.dto.pagination.UserPaginationDto;
import org.bdyb.hotel.exceptions.badRequest.SearchFieldNotExistingException;
import org.bdyb.hotel.exceptions.badRequest.SortFieldNotExistingException;
import org.bdyb.hotel.exceptions.conflict.UserAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoleNotFoundException;
import org.bdyb.hotel.exceptions.notFound.UserIdNotFoundException;
import org.bdyb.hotel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserPaginationResponseDto> getAllUsers(UserPaginationDto userPaginationDto) throws SearchFieldNotExistingException, SortFieldNotExistingException {
        return new ResponseEntity<>(
                userService.searchUsers(userPaginationDto != null ? userPaginationDto : new UserPaginationDto()),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody RegisterDto registerDto) throws UserAlreadyExistsConflictException, RoleNotFoundException {
        userService.registerUser(registerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) throws UserIdNotFoundException {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
