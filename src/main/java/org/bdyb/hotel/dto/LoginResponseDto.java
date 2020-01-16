package org.bdyb.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bdyb.hotel.enums.RoleNameEnum;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

  private String login;
  private String firstName;
  private String lastName;
  private RoleNameEnum roleName;
}