package com.avijit.agencyapp.service;

import com.avijit.agencyapp.common.Constants;
import com.avijit.agencyapp.dto.request.PostRequestDto;
import com.avijit.agencyapp.dto.request.PostUpdateRequestDto;
import com.avijit.agencyapp.entity.LocationEntity;
import com.avijit.agencyapp.entity.PostEntity;
import com.avijit.agencyapp.entity.UserEntity;
import com.avijit.agencyapp.exception.NotFoundException;
import com.avijit.agencyapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.thymeleaf.extras.springsecurity5.util.SpringSecurityContextUtils;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    LocationService locationService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Override
    public PostEntity save(PostRequestDto postRequestDto) throws NotFoundException {

        PostEntity postEntity = new PostEntity(postRequestDto.getStatus());
        postEntity.setPostType(postRequestDto.getPrivacyType());

        LocationEntity locationEntity = locationService.findById(Long.parseLong(postRequestDto.getLocationId()));

        postEntity.setLocation(locationEntity);

        postEntity.setUser(getCurrentUser());

        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity update(PostUpdateRequestDto postUpdateRequestDto) throws NotFoundException {

        PostEntity postEntity = postRepository.findById(postUpdateRequestDto.getId()).orElseThrow(() -> new NotFoundException("Post not found."));
        postEntity.setPostType(postUpdateRequestDto.getPrivacyType());
        postEntity.setStatus(postUpdateRequestDto.getStatus());
        LocationEntity locationEntity = locationService.findById(Long.parseLong(postUpdateRequestDto.getLocationId()));

        postEntity.setLocation(locationEntity);

        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity findById(Long id) throws NotFoundException {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found."));
    }

    private UserEntity getCurrentUser() throws NotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userService.findByEmail(email);
    }
}
