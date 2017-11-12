package org.bdyb.hotel.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bdyb.hotel.enums.IdCardType;

@Getter
@Setter
@Builder
public class RegisterNewUserDto {

    private String email;
    private String password;
    private String name;
    private String surname;

    private IdCardType idCardType;
    private String idCardNumber;
    private Integer monthExpiring;
    private Integer yearExpiring;
}
