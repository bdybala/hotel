package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.exceptions.InternalServerErrorException;
import org.bdyb.hotel.mapper.UserMapper;
import org.bdyb.hotel.mapper.IdentityCardMapper;
import org.bdyb.hotel.repository.UserRepository;
import org.bdyb.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IdentityCardMapper identityCardMapper;


    @Override
    public UserDto findByEmail(String email) throws EntityNotFoundException {
        return userMapper.mapToDto(Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(
                () -> new EntityNotFoundException("User with that email not found! :" + email)));
    }

    @Override
    public UserDto findOne(Long id) throws EntityNotFoundException {
        return userMapper.mapToDto(Optional.ofNullable(userRepository.findOne(id)).orElseThrow(
                () -> new EntityNotFoundException("User with that id not found! :" + id)));
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.mapToDto(userRepository.findAll());
    }

    @Override
    public UserDto save(UserDto newCustomer) throws InternalServerErrorException {
        return userMapper.mapToDto(userRepository.save(userMapper.mapToEntity(newCustomer)));
    }

    @Override
    public UserDto edit(UserDto editedCustomer) throws InternalServerErrorException, EntityNotFoundException {
        User user = userRepository.findOne(editedCustomer.getId());
        return userMapper.mapToDto(userRepository.save(updateCustomer(editedCustomer, user)));
    }

    private User updateCustomer(UserDto editedCustomer, User user) {
        if (editedCustomer.getIdentityCardDto() != null) {
            user.setIdentityCard(identityCardMapper.mapToEntity(editedCustomer.getIdentityCardDto()));
        }
        if (editedCustomer.getName() != null) {
            user.setName(editedCustomer.getName());
        }
        return user;
    }

    @Override
    public void delete(Long id) throws InternalServerErrorException, EntityNotFoundException {
        userRepository.delete(id);
    }
}
