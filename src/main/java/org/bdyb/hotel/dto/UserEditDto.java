package org.bdyb.hotel.dto;

import lombok.*;
import org.bdyb.hotel.enums.RoleNameEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEditDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private RoleNameEnum roleNameEnum;
}
