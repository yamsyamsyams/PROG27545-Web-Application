package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DiceController {

    @RequestMapping("/")
    public ModelAndView index(@RequestParam(defaultValue = "2") int num) {

        // must have a value here in the Game object, on runtime it does not have a value and will not run when trying
        // to retrieve an int num. Make it @NonNull in the model class level.
        Game game = new Game(num);
        game.setDice();

        ModelAndView modelAndView = new ModelAndView("index", "game", game);
        modelAndView.addObject("numOfDice", num);

        return modelAndView;
    }

}
