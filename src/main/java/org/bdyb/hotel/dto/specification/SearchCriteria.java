package org.bdyb.hotel.dto.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    /**
     * Field name
     */
    private String key;
    /**
     * Operation (:)
     */
    private String operation;
    /**
     * Field value
     */
    private Object value;
}
