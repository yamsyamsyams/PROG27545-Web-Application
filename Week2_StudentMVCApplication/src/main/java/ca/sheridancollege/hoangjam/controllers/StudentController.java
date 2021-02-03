package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.model.Student;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    String programs[] = {
            "Network Engineer",
            "Computer Engineer",
            "System Analyst"
    };

    List<Student> students = new ArrayList<Student>(); // CopyOnWriteArrayList for multithreading instead

    @GetMapping("/")
    public String index(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("programs", programs);

        return "input";
    }

    @PostMapping("/processInput")
    public ModelAndView display(Model model, @ModelAttribute Student student) {

        ModelAndView modelAndView = new ModelAndView("output", "myStudents", students);
        students.add(student);
//        model.addAttribute("myStudents", students); if not using ModelAndView

        return modelAndView;
//        return "output"; if not using ModelAndView
    }
}
