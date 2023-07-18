package org.diegodev.hotelalurabackend.repositories;

import org.diegodev.hotelalurabackend.models.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "SELECT * FROM rooms r WHERE r.available_from >= ?1 AND r.available_to <= ?2 AND r.id NOT IN " +
            "(SELECT room_id FROM reservations WHERE (check_in >= ?1 OR check_out <= ?2))", nativeQuery = true)
    List<Room> findAllBetweenDates(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
}