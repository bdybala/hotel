package org.bdyb.hotel.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewVisitDto {

  private Long reservationId;
  private List<GuestDto> guests;

  @Getter
  @Setter
  @ToString
  @NoArgsConstructor
  @AllArgsConstructor
  public static
  class GuestDto {

    private String firstName;
    private String lastName;
    private String pesel;
    private String idCardNumber;
  }
}
