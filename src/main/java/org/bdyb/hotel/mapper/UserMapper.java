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
                .identityCardDto(identityCardMapper.mapToDto(user.getIdentityCard()))
                .build();
        return userDto;
    }

    @Override
    public User mapToEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .build();
    }
}
