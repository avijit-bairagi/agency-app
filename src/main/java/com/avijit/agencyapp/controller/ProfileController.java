package com.avijit.agencyapp.controller;

import com.avijit.agencyapp.common.Constants;
import com.avijit.agencyapp.dto.response.ProfileResponseDto;
import com.avijit.agencyapp.entity.UserEntity;
import com.avijit.agencyapp.exception.NotFoundException;
import com.avijit.agencyapp.service.PostService;
import com.avijit.agencyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping
    public String getPosts(@PageableDefault(page = 0, size = Constants.PAGE_SIZE) Pageable pageable, Model model, Principal principal) throws NotFoundException {

        model.addAttribute("posts", postService.getALlCurrentUserPosts(pageable));
        model.addAttribute("profile", getProfileResponse(principal));

        return "profile/index";
    }

    @GetMapping("/pin/{id}")
    public String doPinPost(@PathVariable("id") Long id, Model model) throws NotFoundException {

        postService.pinPost(id);

        return "redirect:/profile";
    }

    @GetMapping("/unpin")
    public String doUnPinPost() throws NotFoundException {

        postService.unPinPost();

        return "redirect:/profile";
    }

    private ProfileResponseDto getProfileResponse(Principal principal) throws NotFoundException {

        UserEntity userEntity = userService.findByEmail(principal.getName());
        ProfileResponseDto responseDto = new ProfileResponseDto();
        responseDto.setEmail(userEntity.getEmail());
        responseDto.setFullName(userEntity.getFirstName() + " " + userEntity.getLastName());
        if(userEntity.getPinPost() == null) {
            responseDto.setPinStatus("No pin status yet!");
            responseDto.setPinPostId(-1L);
        } else {
            responseDto.setPinStatus(userEntity.getPinPost().getStatus());
            responseDto.setPinPostId(userEntity.getPinPost().getId());
        }

        return responseDto;
    }

}
