package org.bdyb.hotel.repository;

import org.bdyb.hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByNumber(String number);

    // select a.firstName, a.lastName from Book b join b.authors a where b.id = :id


//    List<Room> findAllFree();
}
