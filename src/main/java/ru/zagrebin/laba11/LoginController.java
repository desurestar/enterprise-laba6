package ru.zagrebin.laba11;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login"; // templates/login.html
    }

    @GetMapping("/login-error")
    public String loginError() {
        return "login-error"; // templates/login-error.html
    }
}
