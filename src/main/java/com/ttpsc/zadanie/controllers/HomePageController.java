package com.ttpsc.zadanie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    @GetMapping("/")
    public ModelAndView getHomePage(){
        return new ModelAndView("index.html");
    }
}
