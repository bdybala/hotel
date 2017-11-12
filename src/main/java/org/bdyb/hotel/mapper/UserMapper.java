package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements EntityMapper<User, UserDto> {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private IdentityCardMapper identityCardMapper;

    @Override
    public UserDto mapToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        if (user.getIdentityCard() != null)
            userDto.setIdentityCardDto(identityCardMapper.mapToDto(user.getIdentityCard()));
        return userDto;
    }

    @Override
    public User mapToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (userDto.getIdentityCardDto() != null)
            user.setIdentityCard(identityCardMapper.mapToEntity(userDto.getIdentityCardDto()));
        return user;
    }
}
