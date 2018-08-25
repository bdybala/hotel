package org.bdyb.hotel.dto.pagination;

import lombok.*;
import org.bdyb.hotel.domain.Room;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityResponseDto {
    private Long currentPage;
    private Long totalPages;
    private Long totalRooms;
    private List<Room> availableRooms;
}
