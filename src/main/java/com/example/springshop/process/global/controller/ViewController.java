package com.example.springshop.process.global.controller;

import com.example.springshop.process.domain.user.dto.request.UserJoinDto;
import com.example.springshop.process.domain.user.service.UserService;
import com.example.springshop.process.global.security.auth.PrincipalDetails;
import com.example.springshop.process.global.security.auth.PrincipalDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final PrincipalDetailsService principalDetailsService;
    private final UserService userService;


    @GetMapping("/")
    public String Index(){
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/joinPage")
    public String joinPage(){
        return "join";
    }

    @GetMapping("/loginSuccess")
    public String successLogin(){
        return "login_success";
    }

    @GetMapping("/loginFailed")
    public String failedLogin(){
        return "login_failed";
    }

//    @RequestMapping(value = "/join", method = RequestMethod.POST)
//    public String join(@RequestParam UserJoinDto userJoinDto){
//        System.out.println(userJoinDto.getEmail());
//        System.out.println(userJoinDto.getUserName());
//        System.out.println(userJoinDto.getPhoneNumber());
//        System.out.println(userJoinDto.getPassword());
//        userService.userJoin(userJoinDto);
//        return "redirect:/login";
//    }

    @GetMapping("/userAccess")
    public String userAccess(Model model, Authentication authentication) {
        //Authentication 객체를 통해 유저 정보를 가져올 수 있다.
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();  //PrincipalDetails 객체를 가져옴
        model.addAttribute("info", principalDetails.getUsername() +"의 "+ principalDetails.getUserName()+ "님");      //유저 아이디
        return "user_access";
    }
}