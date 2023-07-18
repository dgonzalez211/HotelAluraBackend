package org.diegodev.hotelalurabackend.services.impl;


import org.diegodev.hotelalurabackend.models.dto.RoomDto;
import org.diegodev.hotelalurabackend.models.dto.mapper.RoomDtoMapper;
import org.diegodev.hotelalurabackend.models.entities.Room;
import org.diegodev.hotelalurabackend.models.request.RoomRequest;
import org.diegodev.hotelalurabackend.repositories.ReservationRepository;
import org.diegodev.hotelalurabackend.repositories.RoomRepository;
import org.diegodev.hotelalurabackend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    @Transactional(readOnly = true)
    public List<RoomDto> findAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms
                .stream()
                .map(r -> RoomDtoMapper.builder().setRoom(r).build())
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public List<Room> getPagedList(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, sortBy);
        Page<Room> pagedResult = roomRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RoomDto> findById(Long id) {
        return roomRepository.findById(id).map(r -> RoomDtoMapper
                .builder()
                .setRoom(r)
                .build());
    }


    @Override
    public List<RoomDto> getAvailableFrom(LocalDate from, LocalDate to) {
        return roomRepository.findAllBetweenDates(from, to).stream().map(r -> RoomDtoMapper
                        .builder()
                        .setRoom(r)
                        .build())
                .toList();
    }


    @Override
    @Transactional
    public RoomDto save(Room room) {
        return RoomDtoMapper.builder().setRoom(roomRepository.save(room)).build();
    }

    @Override
    @Transactional
    public Optional<RoomDto> update(RoomRequest roomRequest, Long id) {
        Optional<Room> room = roomRepository.findById(id);
        Room roomOptional = null;
        if (room.isPresent()) {

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate availableFrom = LocalDate.parse(roomRequest.getAvailableFrom(), dateFormat);
            LocalDate availableTo = LocalDate.parse(roomRequest.getAvailableTo(), dateFormat);

            Room roomDb = room.orElseThrow();
            roomDb.setName(roomRequest.getName());
            roomDb.setDescription(roomRequest.getDescription());
            roomDb.setType(roomRequest.getType());
            roomDb.setAvailable(roomRequest.isAvailable());
            roomDb.setAvailableFrom(availableFrom);
            roomDb.setAvailableTo(availableTo);
            roomOptional = roomRepository.save(roomDb);
        }
        return Optional.ofNullable(RoomDtoMapper.builder().setRoom(roomOptional).build());
    }

    @Override
    @Transactional
    public void remove(Long id) {
        roomRepository.deleteById(id);
    }

}