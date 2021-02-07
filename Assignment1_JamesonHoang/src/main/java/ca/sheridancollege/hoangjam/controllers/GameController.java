package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {

    @RequestMapping("/")
    public ModelAndView index(){
        Game game = new Game();

        ModelAndView modelAndView = new ModelAndView("input", "game", game);

        return modelAndView;
    }

}
