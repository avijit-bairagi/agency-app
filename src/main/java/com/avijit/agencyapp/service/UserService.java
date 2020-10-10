package com.avijit.agencyapp.service;

import com.avijit.agencyapp.dto.request.RegisterRequestDto;
import com.avijit.agencyapp.entity.UserEntity;
import com.avijit.agencyapp.exception.AlreadyExistsException;
import com.avijit.agencyapp.exception.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserEntity findByEmail(String email) throws NotFoundException;

    UserEntity save(RegisterRequestDto registerRequestDto) throws AlreadyExistsException;
}