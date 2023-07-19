package org.diegodev.hotelalurabackend.services.impl;


import org.diegodev.hotelalurabackend.models.dto.HotelDto;
import org.diegodev.hotelalurabackend.models.dto.mapper.HotelDtoMapper;
import org.diegodev.hotelalurabackend.models.entities.Hotel;
import org.diegodev.hotelalurabackend.models.request.HotelRequest;
import org.diegodev.hotelalurabackend.repositories.HotelRepository;
import org.diegodev.hotelalurabackend.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    @Transactional(readOnly = true)
    public List<HotelDto> findAll() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels
                .stream()
                .map(r -> HotelDtoMapper.builder().setHotel(r).build())
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public List<Hotel> getPagedList(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, sortBy);
        Page<Hotel> pagedResult = hotelRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<HotelDto> findById(Long id) {
        return hotelRepository.findById(id).map(r -> HotelDtoMapper
                .builder()
                .setHotel(r)
                .build());
    }


    @Override
    @Transactional
    public HotelDto save(Hotel hotel) {
        return HotelDtoMapper.builder().setHotel(hotelRepository.save(hotel)).build();
    }

    @Override
    @Transactional
    public Optional<HotelDto> update(HotelRequest hotelRequest, Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        Hotel hotelOptional = null;
        if (hotel.isPresent()) {

            Hotel hotelDb = hotel.orElseThrow();
            hotelDb.setName(hotelRequest.getName());
            hotelDb.setAddress(hotelRequest.getAddress());
            hotelDb.setEmail(hotelRequest.getEmail());
            hotelDb.setPhoneNumber(hotelRequest.getPhoneNumber());
            hotelDb.setTelephoneNumber(hotelRequest.getTelephoneNumber());
            hotelOptional = hotelRepository.save(hotelDb);
        }
        return Optional.ofNullable(HotelDtoMapper.builder().setHotel(hotelOptional).build());
    }

    @Override
    @Transactional
    public void remove(Long id) {
        hotelRepository.deleteById(id);
    }

}