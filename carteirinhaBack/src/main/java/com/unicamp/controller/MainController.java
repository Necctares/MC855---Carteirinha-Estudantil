package com.unicamp.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Value("Test server")
    private String message = "Hi there";

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("now", LocalDate.now());
        model.addAttribute("message", this.message);

        return "index";
    }
}
