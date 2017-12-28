package org.bdyb.hotel.init;

import org.bdyb.hotel.domain.Role;
import org.bdyb.hotel.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InitRoles {

    private static final Map<String, String> ROLES;

    static {
        ROLES = new HashMap<>();
        ROLES.put("ROLE_MAID", "Pokojówka");
        ROLES.put("ROLE_RECEPTIONIST", "Recepcjonista");
        ROLES.put("ROLE_MANAGER", "Menadżer");
        ROLES.put("ROLE_ADMINISTRATOR", "Administrator");
    }

    @Autowired
    private RoleRepository roleRepository;

    public void init() {
        ROLES.forEach((name, description) -> {
            if (!roleRepository.existsByName(name)) {
                roleRepository.save(Role.builder()
                        .name(name)
                        .description(description)
                        .build());
            }
        });
    }
}
