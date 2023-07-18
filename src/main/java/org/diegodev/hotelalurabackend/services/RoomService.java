package org.diegodev.hotelalurabackend.services;

import org.diegodev.hotelalurabackend.models.dto.RoomDto;
import org.diegodev.hotelalurabackend.models.entities.Room;
import org.diegodev.hotelalurabackend.models.request.RoomRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomService {

    List<Room> getPagedList(int pageNo, int pageSize, String sortBy);

    List<RoomDto> findAll();

    Optional<RoomDto> findById(Long id);

    List<RoomDto> getAvailableFrom(LocalDate from, LocalDate to);

    RoomDto save(Room room);

    void remove(Long id);

    Optional<RoomDto> update(RoomRequest roomRequest, Long id);


}