package org.bdyb.hotel.init;

import org.bdyb.hotel.domain.Role;
import org.bdyb.hotel.enums.RoleNameEnum;
import org.bdyb.hotel.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitRoles {

    @Autowired
    private RoleRepository roleRepository;

    public void init() {
        for (RoleNameEnum roleNameEnum : RoleNameEnum.values()) {
            if (!roleRepository.existsByName(roleNameEnum)) {
                roleRepository.save(Role.builder()
                        .name(roleNameEnum)
                        .description(roleNameEnum.getDescription())
                        .build());
            }
        }
    }
}
