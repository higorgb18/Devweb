package com.iff.dev_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/")
public class PageController {

    @GetMapping("")
    public String getHome() {
        return "home";
    }

}
