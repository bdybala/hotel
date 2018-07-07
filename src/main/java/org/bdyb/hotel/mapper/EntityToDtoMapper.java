package org.bdyb.hotel.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityToDtoMapper<Entity, Dto> {

    Dto mapToDto(Entity entity);

    default List<Dto> mapToDto(List<Entity> from) {
        return from.stream().map(this::mapToDto).collect(Collectors.toList());
    }

}
