package org.bdyb.hotel.dto.pagination;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortFieldDto {
    private String name;
    private Sort.Direction sortDirection;
}
