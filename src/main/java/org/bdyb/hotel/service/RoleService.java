package org.bdyb.hotel.service;

import org.bdyb.hotel.domain.Role;
import org.bdyb.hotel.dto.RoleDto;
import org.bdyb.hotel.exceptions.EntityNotFoundException;

public interface RoleService extends CrudService<Role, RoleDto> {
    RoleDto findByName(String role_administrator) throws EntityNotFoundException;
}
