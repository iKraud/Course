package ru.sushi.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

      // Login form
    @RequestMapping("/login/")
    public String login() {
        return "login.html";
    }

    // Login form with error
    @RequestMapping("/login-error/")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}