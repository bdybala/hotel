package org.bdyb.hotel.enums;

import lombok.Getter;

public enum RoleNameEnum {
    MAID("Pokojówka"),
    RECEPTIONIST("Recepcjonista"),
    MANAGER("Menadżer"),
    ADMINISTRATOR("Administrator");

    @Getter
    private String description;

    RoleNameEnum(String description) {
        this.description = description;
    }
}
