package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    List<Student> students = new ArrayList<Student>(); // CopyOnWriteArrayList for multithreading instead


    @GetMapping("/")
    public String index(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);

        return "input";
    }

    @PostMapping("/processInput")
    public String display(Model model, @ModelAttribute Student student) {

        students.add(student);
        model.addAttribute("myStudents", students);

        return "output";
    }
}
