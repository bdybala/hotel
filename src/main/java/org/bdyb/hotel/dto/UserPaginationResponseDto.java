package org.bdyb.hotel.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPaginationResponseDto {
    private Long currentPage;
    private Long totalPages;
    private Long totalUsers;
    private List<UserDto> users;
}
