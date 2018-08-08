package org.bdyb.hotel.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDto {

    private Integer currentPage;
    private Integer pageSize;
    private List<SearchFieldDto> searchFields;
    private List<SortFieldDto> sortFields;
}
