package com.avijit.agencyapp.service;

import com.avijit.agencyapp.entity.LocationEntity;
import com.avijit.agencyapp.exception.NotFoundException;
import com.avijit.agencyapp.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<LocationEntity> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public LocationEntity findById(Long id) throws NotFoundException {
        return locationRepository.findById(id).orElseThrow(() -> new NotFoundException("Location not found."));
    }
}
