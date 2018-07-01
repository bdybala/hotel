package org.bdyb.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.domain.Role;
import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.RegisterDto;
import org.bdyb.hotel.exceptions.conflict.UserAlreadyExistsConflictException;
import org.bdyb.hotel.exceptions.notFound.RoleNotFoundException;
import org.bdyb.hotel.repository.RoleRepository;
import org.bdyb.hotel.repository.UserRepository;
import org.bdyb.hotel.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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

    private String getUserFromSecurityContext() {
        // TODO
        return null;
    }
}
