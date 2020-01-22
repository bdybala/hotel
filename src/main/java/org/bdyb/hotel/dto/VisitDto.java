package org.bdyb.hotel.dto;

import java.util.Date;
import java.util.List;
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
public class VisitDto {

  private Long id;
  private RoomDto room;
  private List<GuestDto> guests;
  private Date since;
  private Date upTo;

}
