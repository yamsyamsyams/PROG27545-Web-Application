package ca.sheridancollege.hoangjam.controllers;

import ca.sheridancollege.hoangjam.database.DatabaseAccess;
import ca.sheridancollege.hoangjam.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    @Autowired
    private DatabaseAccess da;

    ModelAndView mv;

    @GetMapping("/")
    public ModelAndView index(Model model){

        mv = new ModelAndView("index","students",da.getStudents());
        mv.addObject("student", new Student());
        return mv;

    }

    @PostMapping("/insertStudent")
    public ModelAndView  processStudent(@ModelAttribute Student student){

        da.insertStudent(student.getName());
        mv = new ModelAndView("index","students",da.getStudents());
        return mv;

    }

    @GetMapping("/deleteStudentById/{id}")
    public ModelAndView  deleteStudent(@PathVariable Long id){

        da.deleteStudentByID(id);
        mv = new ModelAndView("redirect:/","students",da.getStudents());
        return mv;

    }

    @GetMapping("/editStudentById/{id}")
    public ModelAndView  editStudent(@PathVariable Long id){

        Student student = da.getStudent(id).get(0);
        //da.editStudentByID(student);
        mv = new ModelAndView("update","students",da.getStudents());
        mv.addObject("student", student);
        return mv;

    }

    @PostMapping("/updateStudent")
    public ModelAndView  updateStudent(@ModelAttribute Student student){

        da.editStudentByID(student);
        mv = new ModelAndView("redirect:/","students",da.getStudents());
        return mv;

    }

}
