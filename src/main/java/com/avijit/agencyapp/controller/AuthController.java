package com.avijit.agencyapp.controller;

import com.avijit.agencyapp.dto.request.LoginRequestDto;
import com.avijit.agencyapp.dto.request.RegisterRequestDto;
import com.avijit.agencyapp.exception.AlreadyExistsException;
import com.avijit.agencyapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    UserService userService;

    private Logger errorLogger = LoggerFactory.getLogger("error-logger");
    private Logger debugLogger = LoggerFactory.getLogger("debug-logger");

    @GetMapping("/login")
    public String getLogin(LoginRequestDto loginRequestDto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {

            return "redirect:/";
        }
        return "auth/login";
    }

    @GetMapping("/register")
    public String getRegister(RegisterRequestDto registerRequestDto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {

            return "redirect:/";
        }

        return "auth/register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid RegisterRequestDto registerRequestDto, BindingResult bindingResult) {

        debugLogger.info("doRegister() : enter");

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        if (!registerRequestDto.getPassword().equals(registerRequestDto.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "confirmPassword", "Password does not match.");
            return "auth/register";
        }

        try {
            userService.save(registerRequestDto);
        } catch (AlreadyExistsException e) {
            debugLogger.info("doRegister() : {}", e.getLocalizedMessage());
            errorLogger.error(e.getMessage());
            bindingResult.rejectValue("email", "email", e.getMessage());
            return "auth/register";
        }

        debugLogger.info("doRegister() : exit");

        return "redirect:/login";
    }

}
