package com.avijit.agencyapp.service;

import com.avijit.agencyapp.dto.request.PostRequestDto;
import com.avijit.agencyapp.dto.request.PostUpdateRequestDto;
import com.avijit.agencyapp.dto.response.PostResponseDto;
import com.avijit.agencyapp.entity.PostEntity;
import com.avijit.agencyapp.exception.NotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostEntity save(PostRequestDto postRequestDto) throws NotFoundException;

    PostEntity update(PostUpdateRequestDto postUpdateRequestDto) throws NotFoundException;

    PostEntity findById(Long id) throws NotFoundException;

    Page<PostResponseDto> findByPrivacyType(Pageable pageable, String privacyType);

    Page<PostResponseDto> getALlCurrentUserPosts(Pageable pageable) throws NotFoundException;

    void pinPost(Long id) throws NotFoundException;
}
