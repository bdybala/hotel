package org.bdyb.hotel.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomTypeDto {

    private Long id;
    private String name;
    private String description;
}
