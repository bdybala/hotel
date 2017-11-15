package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.IdentityCardDto;
import org.bdyb.hotel.dto.RegisterNewUserDto;
import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.UserMapper;
import org.bdyb.hotel.mapper.IdentityCardMapper;
import org.bdyb.hotel.repository.UserRepository;
import org.bdyb.hotel.service.IdentityCardService;
import org.bdyb.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private IdentityCardService identityCardService;

    @Override
    public UserDto findOne(Long id) throws EntityNotFoundException {
        return userMapper.mapToDto(findOneEntity(id));
    }

    private User findOneEntity(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(userRepository.findOne(id)).orElseThrow(
                () -> new EntityNotFoundException("User with that id not found! :" + id));
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.mapToDto(userRepository.findAll());
    }

    @Override
    public UserDto addOne(UserDto dtoToAdd) throws ConflictException {
        return userMapper.mapToDto(userRepository.save(userMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public UserDto editOne(UserDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        User user = userRepository.findOne(dtoToEdit.getId());
        return userMapper.mapToDto(userRepository.save(updateCustomer(dtoToEdit, user)));
    }

    private User updateCustomer(UserDto editedCustomer, User user) {
        if (editedCustomer.getIdentityCard() != null) {
            user.setIdentityCard(identityCardMapper.mapToEntity(editedCustomer.getIdentityCard()));
        }
        if (editedCustomer.getName() != null) {
            user.setName(editedCustomer.getName());
        }
        return user;
    }

    @Override
    public UserDto delete(Long id) throws EntityNotFoundException {
        UserDto userDto = findOne(id);
        userRepository.delete(id);
        return userDto;
    }

    @Override
    public UserDto findByEmail(String email) throws EntityNotFoundException {
        return userMapper.mapToDto(Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(
                () -> new EntityNotFoundException("User with that email not found! :" + email)));
    }

    @Override
    @Transactional
    public UserDto register(RegisterNewUserDto registerNewUserDto) throws ConflictException {

        IdentityCardDto savedIdentityCardDto = identityCardService.addOne(IdentityCardDto.builder()
                .idCardType(registerNewUserDto.getIdCardType())
                .idCardNumber(registerNewUserDto.getIdCardNumber())
                .monthExpiring(registerNewUserDto.getMonthExpiring())
                .yearExpiring(registerNewUserDto.getYearExpiring())
                .build());

        UserDto savedUser = addOne(UserDto.builder()
                .email(registerNewUserDto.getEmail())
                .name(registerNewUserDto.getName())
                .password(registerNewUserDto.getPassword())
                .identityCard(savedIdentityCardDto)
                .build());

        return savedUser;
    }

}
