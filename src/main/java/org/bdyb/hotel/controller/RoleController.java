package org.bdyb.hotel.controller;

import org.bdyb.hotel.domain.Role;
import org.bdyb.hotel.dto.RoleDto;
import org.bdyb.hotel.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends AbstractCrudController<Role, RoleDto> {

    public RoleController(CrudService<Role, RoleDto> service) {
        super(service);
    }
}
