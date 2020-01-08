package org.bdyb.hotel.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

  private Long roomId;
  private Date since;
  private Date upTo;
  private String firstName;
  private String lastName;
  private String email;
}
