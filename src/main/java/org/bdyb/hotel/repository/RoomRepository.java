package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByNumber(String number);

    @Query(
            "select r from Room r left outer join r.occupiedRooms o left outer join r.reservedRooms rr " +
                    "where (o is null or ?1 > o.upTo or ?2 < o.since) AND " +
                    "(rr is null or ?1 > rr.upTo or ?2 < rr.since)"
    )
    List<Room> findAllFree(Date start, Date end);

    @Query(
            "select r " +
                    "from Room r left outer join r.occupiedRooms o left outer join r.reservedRooms rr join r.roomType rt " +
                    "where rt.name like ?3 AND " +
                    "(o is null or ?1 > o.upTo or ?2 < o.since) AND " +
                    "(rr is null or ?1 > rr.upTo or ?2 < rr.since)"
    )
    List<Room> findAllFreeByRoomType(Date since, Date to, String roomType);


    @Query(
            nativeQuery = true,
            value = "SELECT DISTINCT \n" +
                    "    \tr.id room_id,\n" +
                    "        r.max_capacity max_capacity,\n" +
                    "        r.\"number\" room_number,\n" +
                    "        rt.description room_type,\n" +
                    "        countTotalPrice(r.id, ?2, ?3) total_price\n" +
                    "    FROM public.hotel_rooms r JOIN public.hotel_room_types rt ON r.room_types_id = r.id\n" +
                    "    \tLEFT OUTER JOIN public.hotel_occupied_rooms o ON o.rooms_id = r.id\n" +
                    "        LEFT OUTER JOIN public.hotel_reserved_rooms rr ON rr.rooms_id = r.id\n" +
                    "    WHERE rt.name LIKE ?1 AND\n" +
                    "    \t(o.id IS null OR ?2 > o.up_to OR o.since > ?3) AND\n" +
                    "        (rr.id IS null OR ?2 > rr.up_to OR rr.since > ?3);"
    )
    String[] findRooms(String roomType, Date since, Date to);
}
