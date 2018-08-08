package org.bdyb.hotel.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomPaginationResponseDto {
    private Long currentPage;
    private Long totalPages;
    private Long totalRooms;
    private List<RoomDto> rooms;
}
