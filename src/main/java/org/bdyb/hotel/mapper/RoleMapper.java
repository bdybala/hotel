package org.bdyb.hotel.mapper;

import org.bdyb.hotel.domain.Role;
import org.bdyb.hotel.dto.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements EntityMapper<Role, RoleDto> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public RoleDto mapToDto(Role role) {
        return mapper.map(role, RoleDto.class);
    }

    @Override
    public Role mapToEntity(RoleDto roleDto) {
        return mapper.map(roleDto, Role.class);
    }
}
