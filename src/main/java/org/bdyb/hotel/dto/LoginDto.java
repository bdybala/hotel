package org.bdyb.hotel.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDto {

    private String username;
    private String password;
}
