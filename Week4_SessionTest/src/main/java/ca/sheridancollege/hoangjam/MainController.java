package ca.sheridancollege.hoangjam;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model, HttpSession session, @RequestParam(required = false) String name) {

        String message = "Hello there ";

        if(session.getAttribute("counter") == null){
            session.setAttribute("counter", 1);
//        if (session.isNew()) {
//            session.setAttribute("greeting", message);

        } else {
            session.setAttribute("myName", name);
            session.setAttribute("greeting", "Welcome back " + session.getAttribute("name"));
        }

        return "home";
    }

}
