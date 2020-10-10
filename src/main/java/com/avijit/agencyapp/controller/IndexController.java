package com.avijit.agencyapp.controller;

import com.avijit.agencyapp.common.Constants;
import com.avijit.agencyapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    PostService postService;

    @GetMapping
    public String getHomePage(Model model, @PageableDefault(page = 0, size = Constants.PAGE_SIZE) Pageable pageable) {

        model.addAttribute("posts", postService.findByPrivacyType(pageable, Constants.PUBLIC));

        return "post/index";
    }
}
