package org.diegodev.hotelalurabackend.repositories;

import org.diegodev.hotelalurabackend.models.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}