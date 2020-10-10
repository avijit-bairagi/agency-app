package com.avijit.agencyapp.service;

import com.avijit.agencyapp.entity.LocationEntity;
import com.avijit.agencyapp.exception.NotFoundException;

import java.util.List;

public interface LocationService {

    List<LocationEntity> findAll();

    LocationEntity findById(Long id) throws NotFoundException;
}
