package org.bdyb.hotel.service.impl;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.UserDto;
import org.bdyb.hotel.exceptions.ConflictException;
import org.bdyb.hotel.exceptions.EntityNotFoundException;
import org.bdyb.hotel.mapper.IdentityCardMapper;
import org.bdyb.hotel.mapper.UserMapper;
import org.bdyb.hotel.repository.IdentityCardRepository;
import org.bdyb.hotel.repository.UserRepository;
import org.bdyb.hotel.service.IdentityCardService;
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
    private IdentityCardRepository identityCardRepository;

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
                () -> new EntityNotFoundException("User with that id not found! : " + id));
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.mapToDto(userRepository.findAll());
    }

    @Override
    public UserDto addOne(UserDto dtoToAdd) throws ConflictException {
        if (userRepository.existsByEmail(dtoToAdd.getEmail()))
            throw new ConflictException("That e-mail is already in use! : " + dtoToAdd.getEmail());
        if (identityCardRepository.existsByIdCardNumber(dtoToAdd.getIdentityCard().getIdCardNumber()))
            throw new ConflictException("User with that IdCardNumber is already in db! : " + dtoToAdd.getIdentityCard().getIdCardNumber());
        return userMapper.mapToDto(userRepository.save(userMapper.mapToEntity(dtoToAdd)));
    }

    @Override
    public UserDto editOne(UserDto dtoToEdit) throws EntityNotFoundException, ConflictException {
        User user = findOneEntity(dtoToEdit.getId());
        return userMapper.mapToDto(userRepository.save(updateUser(dtoToEdit, user)));
    }

    private User updateUser(UserDto editedUser, User user) {
        if (editedUser.getIdentityCard() != null) {
            user.setIdentityCard(identityCardMapper.mapToEntity(editedUser.getIdentityCard()));
        }
        if (editedUser.getName() != null) {
            user.setName(editedUser.getName());
        }
        if (editedUser.getSurname() != null) {
            user.setSurname(editedUser.getSurname());
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

}
