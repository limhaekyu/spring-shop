package com.example.springshop.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String Index(){
        return "index";
    }

    @GetMapping("/login")
    public String Login(){
        return "login";
    }
}
