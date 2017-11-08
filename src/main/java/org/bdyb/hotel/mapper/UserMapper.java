package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements EntityMapper<User, UserDto> {

    @Autowired
    private IdentityCardMapper identityCardMapper;

    @Override
    public UserDto mapToDto(User user) {
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
        if (user.getIdentityCard() != null)
            userDto.setIdentityCardDto(identityCardMapper.mapToDto(user.getIdentityCard()));
        return userDto;
    }

    @Override
    public User mapToEntity(UserDto userDto) {
        User user = User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .build();
        if (userDto.getIdentityCardDto() != null)
            user.setIdentityCard(identityCardMapper.mapToEntity(userDto.getIdentityCardDto()));
        return user;
    }
}
