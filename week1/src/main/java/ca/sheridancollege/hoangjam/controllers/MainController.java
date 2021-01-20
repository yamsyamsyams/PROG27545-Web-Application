package ca.sheridancollege.hoangjam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping("/")
    public String processIndex() {

        return "index";
    } // methods like this redirect to html pages. return "index" has .html automatically appended to it

    @RequestMapping("/customHello") // localhost:8080/customHello
    public String process(Model model, @RequestParam String fullName){

        model.addAttribute("name", fullName);
        return "output";
    }
}
