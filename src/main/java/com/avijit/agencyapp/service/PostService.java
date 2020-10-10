package com.avijit.agencyapp.service;

import com.avijit.agencyapp.dto.request.PostRequestDto;
import com.avijit.agencyapp.dto.request.PostUpdateRequestDto;
import com.avijit.agencyapp.entity.PostEntity;
import com.avijit.agencyapp.exception.NotFoundException;

public interface PostService {

    PostEntity save(PostRequestDto postRequestDto) throws NotFoundException;


    PostEntity update(PostUpdateRequestDto postUpdateRequestDto) throws NotFoundException;

    PostEntity findById(Long id) throws NotFoundException;
}
