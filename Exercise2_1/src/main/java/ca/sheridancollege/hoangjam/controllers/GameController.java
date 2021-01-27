package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class GameController {
    List<Game> gamesList = new CopyOnWriteArrayList<Game>();

    @GetMapping("/")
    public String index(Model model) {
        Game game = new Game();
        model.addAttribute("game", game);

        return "index";
    }

    @PostMapping("/processInput")
    public String display(Model model, @ModelAttribute Game game){
        gamesList.add(game);
        model.addAttribute("gamesList", gamesList);

        return "index";
    }
}
