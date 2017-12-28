package org.bdyb.hotel.init;

import org.bdyb.hotel.domain.RoomType;
import org.bdyb.hotel.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InitRoomTypes {
    private static final Map<String, String> ROOM_TYPES;

    static {
        ROOM_TYPES = new HashMap<>();
        ROOM_TYPES.put("SINGLE_ROOM", "Jedynka");
        ROOM_TYPES.put("TWIN_ROOM", "Podwójny");
        ROOM_TYPES.put("DOUBLE_ROOM", "Dwójka");
        ROOM_TYPES.put("TRIPLE", "Trójka");
        ROOM_TYPES.put("QUAD", "Czwórka");
        ROOM_TYPES.put("DORMITORY", "Pokój typu Dormitory");
    }

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public void init() {
        ROOM_TYPES.forEach((name, description) -> {
            if (!roomTypeRepository.existsByName(name)) {
                roomTypeRepository.save(RoomType.builder()
                        .name(name)
                        .description(description)
                        .build());
            }
        });
    }
}
