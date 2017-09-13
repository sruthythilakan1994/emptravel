package com.assigment.emptravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
class HomeController {

    @ModelAttribute("module")
    String module() {
        return "home";
    }

    @GetMapping("/test")
    String index(Principal principal) {
        return principal != null ? "homeSignedIn" : "homeNotSignedIn";
    }
}
