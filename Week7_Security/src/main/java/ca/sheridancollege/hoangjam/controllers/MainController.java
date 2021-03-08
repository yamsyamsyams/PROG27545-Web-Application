package ca.sheridancollege.hoangjam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String returnIndex(){

        return "index";
    }


}
