package ca.sheridancollege.hoangjam.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainRestController {

    @RequestMapping("/helloAll")
    public String helloAll(){

        return "Hello All";
    } // because this is a REST controller and not a controller, .html is not appended

    // test push here
}
