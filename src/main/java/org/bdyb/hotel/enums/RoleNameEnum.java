package org.bdyb.hotel.enums;

import lombok.Getter;

public enum RoleNameEnum {
    ROLE_MAID("Pokojówka"),
    ROLE_RECEPTIONIST("Recepcjonista"),
    ROLE_MANAGER("Menadżer"),
    ROLE_ADMINISTRATOR("Administrator");

    @Getter
    private String description;

    RoleNameEnum(String description) {
        this.description = description;
    }
}
