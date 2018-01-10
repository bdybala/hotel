package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.LoginDto;
import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.exceptions.LoginFailedException;
import org.bdyb.hotel.mapper.UserMapper;
import org.bdyb.hotel.repository.UserRepository;
import org.bdyb.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto findOne(Long id) throws EntityNotFoundException {
        return userMapper.mapToDto(Optional.ofNullable(userRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("User not found with that ID! : " + id)));
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.mapToDto(userRepository.findAll());
    }

    @Override
    public UserDto addOne(UserDto dtoToAdd) throws ConflictException {
        if (userRepository.existsByEmail(dtoToAdd.getEmail()))
            throw new ConflictException("User already exists with that Email! : " + dtoToAdd);
        if (userRepository.existsByUsername(dtoToAdd.getUsername()))
            throw new ConflictException("User already exists with that Username! : " + dtoToAdd);
        return userMapper.mapToDto(userRepository.save(userMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public UserDto editOne(UserDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        User user = Optional.ofNullable(userRepository.findOne(dtoToEdit.getId()))
                .orElseThrow(() -> new EntityNotFoundException("User to edit not found with that ID! : " + dtoToEdit));
        return userMapper.mapToDto(userRepository.save(updateUser(user, dtoToEdit)));
    }

    private User updateUser(User user, UserDto dtoToEdit) {
        if (dtoToEdit.getFirstName() != null) {
            user.setFirstName(dtoToEdit.getFirstName());
        }
        if (dtoToEdit.getLastName() != null) {
            user.setLastName(dtoToEdit.getLastName());
        }
        if (dtoToEdit.getPassword() != null) {
            user.setPassword(dtoToEdit.getPassword());
        }
        if (dtoToEdit.getEmail() != null) {
            user.setEmail(dtoToEdit.getEmail());
        }
        return user;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!userRepository.exists(id))
            throw new EntityNotFoundException("User to delete not found with that ID! : " + id);
        userRepository.delete(id);
    }

    @Override
    public UserDto login(LoginDto loginDto) throws LoginFailedException {
        return userMapper.mapToDto(Optional.ofNullable(userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword()))
                .orElseThrow(() -> new LoginFailedException("Login unsuccessful for: " + loginDto.getUsername())));
    }

    @Override
    public List<UserDto> findByUsernameAndRoleName(String username, String roleName) {
        return userMapper.mapToDto(userRepository.findByUsernameAndRoleId(
                username == null ? "" : username,
                roleName == null ? "" : roleName));
    }
}
