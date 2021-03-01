package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.database.DatabaseAccess;
import ca.sheridancollege.hoangjam.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TeamsController {

    @Autowired
    private DatabaseAccess da;

    ModelAndView mv;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/displayResults")
    public ModelAndView displayResults() {
        mv = new ModelAndView("displayResults", "teams", da.getTeams());
        mv.addObject("team", new Team());
        return mv;
    }

    @RequestMapping("/goToEditTeam")
    public ModelAndView goToEditTeam(){
        mv = new ModelAndView("editTeam", "teams", da.getTeams());
        mv.addObject("team", new Team());
        return mv;
    }

    @GetMapping("/editTeamForm/{id}")
    public ModelAndView editTeam(@PathVariable Long id){
        Team team = da.getTeam(id).get(0);
        mv = new ModelAndView("editTeamForm", "teams", da.getTeams());
        mv.addObject("team", team);
        return mv;
    }

    @PostMapping("/updateTeam")
    public ModelAndView updateTeam(@ModelAttribute Team team){
        da.editTeamByID(team);
        mv = new ModelAndView("redirect:/displayResults", "teams", da.getTeams());
        return mv;
    }

    @RequestMapping("/goToDeleteTeam")
    public ModelAndView goToDeleteTeam(){
        mv = new ModelAndView("deleteTeam", "teams", da.getTeams());
        mv.addObject("team", new Team());
        return mv;
    }

    @GetMapping("/deleteTeamForm/{id}")
    public ModelAndView deleteTeam(@PathVariable Long id){
        Team team = da.getTeam(id).get(0);
        mv = new ModelAndView("redirect:/displayResults", "teams", da.getTeams());
        mv.addObject("team", team);
        return mv;
    }

    @RequestMapping("/goToAddTeam")
    public ModelAndView goToAddTeam(){
        mv = new ModelAndView("addTeam", "teams", da.getTeams());
        mv.addObject("team", new Team());
        return mv;
    }

    @PostMapping("/addTeamForm")
    public ModelAndView addTeam(@ModelAttribute Team team){
        da.addTeam(team.getTeamName(), team.getContinent(), team.getPlayed(), team.getWon(), team.getDrawn(), team.getLost());
        mv = new ModelAndView("addTeam", "teams", da.getTeams());
        return mv;
    }


}
