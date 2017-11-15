package org.bdyb.hotel.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityMapper<Entity, Dto> {

    Dto mapToDto(Entity entity);

    Entity mapToEntity(Dto dto);

    default List<Dto> mapToDto(List<Entity> from) {
        return from.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    default List<Entity> mapToEntity(List<Dto> to) {
        return to.stream().map(this::mapToEntity).collect(Collectors.toList());
    }
}
