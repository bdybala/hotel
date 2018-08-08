package org.bdyb.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.config.Constants;
import org.bdyb.hotel.domain.Role;
import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.RegisterDto;
import org.bdyb.hotel.dto.UserPaginationResponseDto;
import org.bdyb.hotel.dto.pagination.PaginationDto;
import org.bdyb.hotel.exceptions.badRequest.SearchFieldNotExistingException;
import org.bdyb.hotel.exceptions.badRequest.SortFieldNotExistingException;
import org.bdyb.hotel.exceptions.conflict.UserAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoleNotFoundException;
import org.bdyb.hotel.exceptions.notFound.UserIdNotFoundException;
import org.bdyb.hotel.repository.RoleRepository;
import org.bdyb.hotel.repository.UserDao;
import org.bdyb.hotel.repository.UserRepository;
import org.bdyb.hotel.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User registerUser(RegisterDto registerDto) throws UserAlreadyExistsConflictException, RoleNotFoundException {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new UserAlreadyExistsConflictException();
        }
        Role role = roleRepository.findByName(registerDto.getRoleNameEnum())
                .orElseThrow(RoleNotFoundException::new);

        return userRepository.save(User.builder()
                .createdBy(getUserFromSecurityContext())
                .email(registerDto.getEmail())
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .password(registerDto.getPassword())
                .role(role)
                .build());
    }

    @Override
    public UserPaginationResponseDto searchUsers(PaginationDto userPaginationDto) throws SearchFieldNotExistingException, SortFieldNotExistingException {
        if (userPaginationDto.getCurrentPage() == null) userPaginationDto.setCurrentPage(1);
        if (userPaginationDto.getPageSize() == null) userPaginationDto.setPageSize(Constants.DEFAULT_PAGE_SIZE);
        return userDao.findUsers(userPaginationDto);
    }

    @Override
    public void deleteById(Long id) throws UserIdNotFoundException {
        User user = userRepository.findOne(id);
        if (user == null) throw new UserIdNotFoundException();
        userRepository.delete(user);
    }

    private String getUserFromSecurityContext() {
        // TODO jutro nie ide do pracy
        return null;
    }
}
