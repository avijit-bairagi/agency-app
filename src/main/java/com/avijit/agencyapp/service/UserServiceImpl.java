package com.avijit.agencyapp.service;

import com.avijit.agencyapp.dto.request.RegisterRequestDto;
import com.avijit.agencyapp.entity.UserEntity;
import com.avijit.agencyapp.exception.AlreadyExistsException;
import com.avijit.agencyapp.exception.NotFoundException;
import com.avijit.agencyapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
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

    public UserEntity findByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found."));
    }

    @Override
    public UserEntity save(RegisterRequestDto registerRequestDto) throws AlreadyExistsException {

        if (userRepository.findByEmail(registerRequestDto.getEmail()).isPresent()) {
            throw new AlreadyExistsException("Email already exists!");
        }

        UserEntity userEntity = modelMapper.map(registerRequestDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
        return new User(user.getEmail(), user.getPassword(), Collections.EMPTY_LIST);
    }
}