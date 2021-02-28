package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.database.DatabaseAccess;
import ca.sheridancollege.hoangjam.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResultsController {

    @Autowired
    private DatabaseAccess da;

    ModelAndView mv;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/addTeam")
    public ModelAndView addTeam(@ModelAttribute Team team) {
        da.addTeam(team.getTeamName());
        mv = new ModelAndView("addTeam", "teams", da.getTeams());
        return mv;
    }



}
