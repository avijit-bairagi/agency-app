package com.avijit.agencyapp.controller;

import com.avijit.agencyapp.common.Constants;
import com.avijit.agencyapp.dto.request.PostRequestDto;
import com.avijit.agencyapp.dto.request.PostUpdateRequestDto;
import com.avijit.agencyapp.entity.PostEntity;
import com.avijit.agencyapp.exception.NotFoundException;
import com.avijit.agencyapp.service.LocationService;
import com.avijit.agencyapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    LocationService locationService;

    @Autowired
    PostService postService;

    @GetMapping
    public String getPosts() {

        return "post/index";
    }

    @GetMapping("/create")
    public String getPostCreateView(PostRequestDto postRequestDto, Model model) {
        setPostRequestModelAttribute(model);
        return "post/create";
    }

    @PostMapping("/create")
    public String processPostRequest(@Valid PostRequestDto postRequestDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            setPostRequestModelAttribute(model);
            return "post/create";
        }

        try {
            postService.save(postRequestDto);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return "redirect:/post";
    }

    @GetMapping("/edit/{id}")
    public String getPostEditView(@PathVariable("id") Long id, Model model) throws NotFoundException {

        PostEntity postEntity = postService.findById(id);

        PostUpdateRequestDto postUpdateRequestDto = getPostUpdateRequestDto(postEntity);

        model.addAttribute("postUpdateRequestDto", postUpdateRequestDto);
        setPostRequestModelAttribute(model);

        return "post/edit";
    }

    @PostMapping("/edit/{id}")
    public String processPostUpdateRequest(@PathVariable("id") Long id, @Valid PostUpdateRequestDto postUpdateRequestDto, BindingResult bindingResult, Model model) throws NotFoundException {

        if (bindingResult.hasErrors()) {
            setPostRequestModelAttribute(model);
            return "post/edit";
        }

        postService.update(postUpdateRequestDto);

        return "redirect:/post";
    }

    private PostUpdateRequestDto getPostUpdateRequestDto(PostEntity postEntity) {

        PostUpdateRequestDto postUpdateRequestDto = new PostUpdateRequestDto(postEntity.getId(), postEntity.getStatus());
        postUpdateRequestDto.setLocationId(String.valueOf(postEntity.getLocation().getId()));
        postUpdateRequestDto.setPrivacyType(postEntity.getPostType());
        return postUpdateRequestDto;
    }

    private void setPostRequestModelAttribute(Model model) {
        model.addAttribute("privacyTypes", Constants.PRIVACY_TYPES);
        model.addAttribute("locations", locationService.findAll());
    }

}
