package org.bdyb.hotel.dto.pagination;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bdyb.hotel.dto.RoomDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityResponseDto {

  private Long currentPage;
  private Long totalPages;
  private Long totalRooms;
  private List<RoomDto> availableRooms;
}
