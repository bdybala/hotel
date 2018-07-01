package org.bdyb.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bdyb.hotel.enums.RoleNameEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private RoleNameEnum roleNameEnum;
}
