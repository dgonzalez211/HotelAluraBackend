package org.diegodev.hotelalurabackend.services;

import org.diegodev.hotelalurabackend.models.dto.HotelDto;
import org.diegodev.hotelalurabackend.models.entities.Hotel;
import org.diegodev.hotelalurabackend.models.request.HotelRequest;

import java.util.List;
import java.util.Optional;

public interface HotelService {

    List<Hotel> getPagedList(int pageNo, int pageSize, String sortBy);

    List<HotelDto> findAll();

    Optional<HotelDto> findById(Long id);

    HotelDto save(Hotel hotel);

    void remove(Long id);

    Optional<HotelDto> update(HotelRequest hotelRequest, Long id);


}