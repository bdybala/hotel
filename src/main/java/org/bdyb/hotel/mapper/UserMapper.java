package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements EntityMapper<User, UserDto> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public UserDto mapToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    @Override
    public User mapToEntity(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }
}
