package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.database.DatabaseAccess;
import ca.sheridancollege.hoangjam.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    @Autowired
    DatabaseAccess da;

    @GetMapping("/")
    public ModelAndView index(){

        return new ModelAndView("index", "student", new Student());
    }

    @PostMapping("/insertStudent")
    public ModelAndView processStudent(@ModelAttribute Student student){

        da.insertStudent(student.getName());

        return new ModelAndView("index", "students", da.getStudents());
    }

}
