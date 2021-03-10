package ca.sheridancollege.hoangjam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String returnIndex() {
        return "index";
    }

    @GetMapping("/secure")
    public String secureIndex() {
        return "/secure/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/permission_denied")
    public String permissionDenied() {
        return "/error/permission_denied";
    }


}
