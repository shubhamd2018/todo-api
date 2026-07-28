package com.shubham.todoapi.controller;

import com.shubham.todoapi.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    @GetMapping("/")
    public String welcome(){
        return greetingService.getWelcomeMessg();
    }
}
