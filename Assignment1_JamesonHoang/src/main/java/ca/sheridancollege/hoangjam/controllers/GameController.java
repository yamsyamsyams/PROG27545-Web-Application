package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {

    String[] choiceNames = {
            "Paper",
            "Rock",
            "Scissors"};

    @RequestMapping("/")
    public ModelAndView index(@RequestParam(defaultValue = "0") int choice){
        Game game = new Game();
        ModelAndView modelAndView = new ModelAndView("input", "game", game);
        modelAndView.addObject("userChoice", choice);
        modelAndView.addObject("choiceNames", choiceNames);

        return modelAndView;
    }

    @PostMapping("processInput")
    public String processChoice(Model model, @ModelAttribute Game game){
        model.addAttribute("choice", game.getUserChoice());


        return "output";
    }


}
