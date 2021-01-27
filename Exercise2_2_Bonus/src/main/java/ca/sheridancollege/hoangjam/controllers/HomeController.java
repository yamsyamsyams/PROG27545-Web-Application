package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.model.Grommet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    List<Grommet> grommets = new ArrayList<Grommet>();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/grommetFactory")
    public String processGrommets(Model model,
                                  @RequestParam int numRed,
                                  @RequestParam int numBlue,
                                  @RequestParam int numGreen,
                                  @RequestParam String redSymbol,
                                  @RequestParam String blueSymbol,
                                  @RequestParam String greenSymbol
    ) {
        for (int i = 1; i <= numRed; i++) {
            grommets.add(new Grommet("Red", redSymbol));
        }
        for (int i = 1; i <= numBlue; i++) {
            grommets.add(new Grommet("Blue", blueSymbol));
        }
        for (int i = 1; i <= numGreen; i++) {
            grommets.add(new Grommet("Green", greenSymbol));
        }

        int total = grommets.size();
        int bonusGrommets = total / 20;

        for (int i = 0; i < bonusGrommets; i++) {
            grommets.add(new Grommet("Magenta", "#"));
        }

        model.addAttribute("grommetList", grommets);

        return "ListGrommets";
    }

}
