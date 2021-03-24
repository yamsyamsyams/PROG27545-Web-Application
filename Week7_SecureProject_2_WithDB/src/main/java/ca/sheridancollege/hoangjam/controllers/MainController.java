package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    @Lazy
    private DatabaseAccess da;

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

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam
            String password) {
        da.addUser(username, password);
        Long userId= da.findUserAccount(username).getUserId();
        da.addRole(userId, Long.valueOf(2));
        return "redirect:/";
    }


}
