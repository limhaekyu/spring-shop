package com.example.springshop.process.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String Index(){
        return "index";
    }

    @RequestMapping("/login")
    public String showlogin(){
        return "login";
    }
}
