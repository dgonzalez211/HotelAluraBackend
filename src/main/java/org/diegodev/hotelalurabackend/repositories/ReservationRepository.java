package org.diegodev.hotelalurabackend.repositories;

import org.diegodev.hotelalurabackend.models.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}