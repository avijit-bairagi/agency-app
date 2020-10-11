package com.avijit.agencyapp.service;

import com.avijit.agencyapp.common.Constants;
import com.avijit.agencyapp.dto.request.RegisterRequestDto;
import com.avijit.agencyapp.entity.UserEntity;
import com.avijit.agencyapp.exception.AlreadyExistsException;
import com.avijit.agencyapp.exception.NotFoundException;
import com.avijit.agencyapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger errorLogger = LoggerFactory.getLogger("error-logger");
    private final Logger debugLogger = LoggerFactory.getLogger("debug-logger");

    public UserEntity findByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            errorLogger.error(Constants.USER_NOT_FOUND);
            return new NotFoundException(Constants.USER_NOT_FOUND);
        });
    }

    @Override
    public UserEntity save(RegisterRequestDto registerRequestDto) throws AlreadyExistsException {

        debugLogger.info("save() : enter");

        if (userRepository.findByEmail(registerRequestDto.getEmail()).isPresent()) {
            throw new AlreadyExistsException(Constants.EMAIL_ALREADY_EXISTS);
        }

        UserEntity userEntity = modelMapper.map(registerRequestDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        debugLogger.info("save() : exit");

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> {
            errorLogger.error(Constants.INVALID_USERNAME_OR_PASSWORD);
            return new UsernameNotFoundException(Constants.INVALID_USERNAME_OR_PASSWORD);
        });
        return new User(user.getEmail(), user.getPassword(), Collections.EMPTY_LIST);
    }
}