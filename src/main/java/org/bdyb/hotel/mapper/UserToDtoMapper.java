package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoMapper implements EntityToDtoMapper<User, UserDto> {
    @Override
    public UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleName(user.getRole().getName().name())
                .build();
    }
}
