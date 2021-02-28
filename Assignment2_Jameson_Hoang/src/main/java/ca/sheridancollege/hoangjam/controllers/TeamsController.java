package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.database.DatabaseAccess;
import ca.sheridancollege.hoangjam.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TeamsController {

    @Autowired
    private DatabaseAccess da;

    ModelAndView mv;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/displayResults")
    public ModelAndView goToDisplayResults(){
        mv = new ModelAndView("displayResults", "teams", da.getTeams());
        mv.addObject("team", new Team());
        return mv;
    }

    // he wants redirect to an addTeam page first before processing.

    @RequestMapping("/goToAddTeam")
    public String goToAddTeam(){
        return "addTeam";
    }

    @PostMapping("/addTeam")
    public ModelAndView addTeam(@ModelAttribute Team team){
        da.addTeam(team.getTeamName(), team.getContinent(), team.getPlayed(), team.getWon(), team.getDrawn(), team.getLost());
        mv = new ModelAndView("displayResults", "teams", da.getTeams());
        return mv;
    }



}
