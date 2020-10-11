package com.avijit.agencyapp.service;

import com.avijit.agencyapp.common.Constants;
import com.avijit.agencyapp.dto.request.PostRequestDto;
import com.avijit.agencyapp.dto.request.PostUpdateRequestDto;
import com.avijit.agencyapp.dto.response.PostResponseDto;
import com.avijit.agencyapp.entity.LocationEntity;
import com.avijit.agencyapp.entity.PostEntity;
import com.avijit.agencyapp.entity.UserEntity;
import com.avijit.agencyapp.exception.NotFoundException;
import com.avijit.agencyapp.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    LocationService locationService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    private Logger log = LoggerFactory.getLogger("error-logger");

    @Override
    public PostEntity save(PostRequestDto postRequestDto) throws NotFoundException {

        PostEntity postEntity = new PostEntity(postRequestDto.getStatus());
        postEntity.setPrivacyType(postRequestDto.getPrivacyType());

        LocationEntity locationEntity = locationService.findById(Long.parseLong(postRequestDto.getLocationId()));

        postEntity.setLocation(locationEntity);

        postEntity.setUser(getCurrentUser());

        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity update(PostUpdateRequestDto postUpdateRequestDto) throws NotFoundException {

        PostEntity postEntity = postRepository.findById(postUpdateRequestDto.getId()).orElseThrow(() -> {
            log.error(Constants.POST_NOT_FOUND);
            return new NotFoundException(Constants.POST_NOT_FOUND);
        });
        postEntity.setPrivacyType(postUpdateRequestDto.getPrivacyType());
        postEntity.setStatus(postUpdateRequestDto.getStatus());
        LocationEntity locationEntity = locationService.findById(Long.parseLong(postUpdateRequestDto.getLocationId()));

        postEntity.setLocation(locationEntity);

        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity findById(Long id) throws NotFoundException {
        return postRepository.findById(id).orElseThrow(() -> {
            log.error(Constants.POST_NOT_FOUND);
            return new NotFoundException(Constants.POST_NOT_FOUND);
        });
    }

    @Override
    public Page<PostResponseDto> findByPrivacyType(Pageable pageable, String privacyType) {

        Page<PostEntity> postEntities = postRepository.findByPrivacyType(getPageRequest(pageable), privacyType);

        return new PageImpl<>(getResponseDtos(postEntities), pageable, postEntities.getTotalElements());
    }

    @Override
    public Page<PostResponseDto> getALlCurrentUserPosts(Pageable pageable) throws NotFoundException {

        UserEntity userEntity = getCurrentUser();

        Page<PostEntity> postEntities = postRepository.findAllCurrentUserPost(pageable, userEntity.getId());

        return new PageImpl<>(getResponseDtos(postEntities), pageable, postEntities.getTotalElements());
    }

    @Override
    public void pinPost(Long id) throws NotFoundException {

        PostEntity pinPost = postRepository.findById(id).orElseThrow(() -> {
            log.error(Constants.POST_NOT_FOUND);
            return new NotFoundException(Constants.POST_NOT_FOUND);
        });

        UserEntity userEntity = getCurrentUser();

        userEntity.setPinPost(pinPost);

        userService.update(userEntity);
    }

    @Override
    public void unPinPost() throws NotFoundException {

        UserEntity userEntity = getCurrentUser();

        userEntity.setPinPost(null);

        userService.update(userEntity);
    }

    private List<PostResponseDto> getResponseDtos(Page<PostEntity> postEntities) {

        List<PostResponseDto> responseDtos = new ArrayList<>();

        postEntities.forEach(postEntity -> {

            PostResponseDto responseDto = new PostResponseDto();
            responseDto.setId(postEntity.getId());
            responseDto.setPostBy(postEntity.getUser().getFirstName() + " " + postEntity.getUser().getLastName());
            responseDto.setLocation(postEntity.getLocation().getName());
            responseDto.setStatus(postEntity.getStatus());
            responseDto.setPostAt(convertToLocalDate(postEntity.getCreatedAt()));
            responseDto.setPrivacyType(postEntity.getPrivacyType());
            responseDtos.add(responseDto);

        });

        return responseDtos;
    }

    private UserEntity getCurrentUser() throws NotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userService.findByEmail(email);
    }

    private String convertToLocalDate(Date date) {
        return new SimpleDateFormat(Constants.DATE_FORMAT_yyyy_MM_dd_hh_mm_a).format(date);
    }

    private Pageable getPageRequest(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "updatedAt"));
    }
}
