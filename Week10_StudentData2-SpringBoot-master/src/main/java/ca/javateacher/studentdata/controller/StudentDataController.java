package ca.javateacher.studentdata.controller;

import ca.javateacher.studentdata.data.StudentDataService;
import ca.javateacher.studentdata.model.StudentForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentDataController {

    private final Logger logger = LoggerFactory.getLogger(StudentDataController.class);

    private static final String[] programs = {
            "Computer Programmer", "Systems Technology",
            "Engineering Technician", "Systems Technician"};

    private StudentDataService studentDataService;

    public StudentDataController(
            @Qualifier("studentDataServiceJpaImpl") StudentDataService studentDataService){
        this.studentDataService = studentDataService;
    }

    @GetMapping(value={"/", "/Index"})
    public String index(){
        logger.trace("index() is called");
        return "students/Index";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/AddStudent")
    public ModelAndView addStudent(){
        logger.trace("addStudent() is called");
        ModelAndView modelAndView =
                new ModelAndView("students/AddStudent",
                                    "form", new StudentForm());
        modelAndView.addObject("programs", programs);
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/InsertStudent")
    public String insertStudent(
            @Validated @ModelAttribute("form") StudentForm form,
            BindingResult bindingResult,
            Model model){
        logger.trace("insertStudent() is called");
        // checking for the input validation errors
        if (bindingResult.hasErrors()) {
            logger.trace("input validation errors");
            //model.addAttribute("form", form);
            model.addAttribute("programs", programs);
            return "students/AddStudent";
        } else {
            logger.trace("the user inputs are correct");
            studentDataService.insertStudentForm(form);
            return "redirect:/students/ConfirmInsert/" + form.getId();
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/ConfirmInsert/{id}")
    public String confirmInsert(@PathVariable(name = "id") String strId, Model model){
        logger.trace("confirmInsert() is called");
        try {
            int id = Integer.parseInt(strId);
            logger.trace("looking for the data in the database");
            StudentForm form = studentDataService.getStudentForm(id);
            if (form == null) {
                logger.trace("no data for this id=" + id);
                return "students/DataNotFound";
            } else {
                logger.trace("showing the data");
                model.addAttribute("student", form);
                return "students/ConfirmInsert";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id in not an integer");
            return "students/DataNotFound";
        }
    }

    @GetMapping("/ListStudents")
    public ModelAndView listStudents() {
        logger.trace("listStudents() is called");
        List<StudentForm> list = studentDataService.getAllStudentForms();
        return new ModelAndView("students/ListStudents",
                                "students", list);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/DeleteAll")
    public String deleteAll(){
        logger.trace("deleteAll() is called");
        studentDataService.deleteAllStudentForms();
        return "redirect:/students/ListStudents";
    }

    @GetMapping("StudentDetails/{id}")
    public String studentDetails(@PathVariable String id, Model model){
        logger.trace("studentDetails() is called");
        try {
            StudentForm form = studentDataService.getStudentForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("student", form);
                return "students/StudentDetails"; // show the student data in the form to edit
            } else {
                logger.trace("no data for this id=" + id);
                return "students/DataNotFound";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
            return "students/DataNotFound";
        }
    }

    // a user clicks "Delete" link (in the table) to "DeleteStudent"
    @Secured("ROLE_ADMIN")
    @GetMapping("/DeleteStudent")
    public String deleteStudent(@RequestParam String id, Model model) {
        logger.trace("deleteStudent() is called");
        try {
            StudentForm form = studentDataService.getStudentForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("student", form);
                return "students/DeleteStudent"; // ask "Do you really want to remove?"
            } else {
                return "redirect:/students/ListStudents";
            }
        } catch (NumberFormatException e) {
            return "redirect:/students/ListStudents";
        }
    }

    // a user clicks "Remove Record" button in "DeleteStudent" page,
    // the form submits the data to "RemoveStudent"
    @Secured("ROLE_ADMIN")
    @PostMapping("/RemoveStudent")
    public String removeStudent(@RequestParam String id) {
        logger.trace("removeStudent() is called");
        try {
            studentDataService.deleteStudentForm(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
        }
        return "redirect:/students/ListStudents";
    }

    // a user clicks "Edit" link (in the table) to "EditStudent"
    @Secured("ROLE_ADMIN")
    @GetMapping("/EditStudent")
    public String editStudent(@RequestParam String id, Model model) {
        logger.trace("editStudent() is called");
        try {
            StudentForm form = studentDataService.getStudentForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("form", form);
                model.addAttribute("programs", programs);
                return "students/EditStudent";
            } else {
                logger.trace("no data for this id=" + id);
                return "redirect:/students/ListStudents";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
            return "redirect:/students/ListStudents";
        }
    }

    // the form submits the data to "UpdateStudent"
    @Secured("ROLE_ADMIN")
    @PostMapping("/UpdateStudent")
    public String updateStudent(
            @Validated @ModelAttribute("form") StudentForm form,
            BindingResult bindingResult,
            Model model) {
        logger.trace("updateStudent() is called");
        // checking for the input validation errors
        if (bindingResult.hasErrors()) {
            logger.trace("input validation errors");
            //model.addAttribute("form", form);
            model.addAttribute("programs", programs);
            return "students/EditStudent";
        } else {
            logger.trace("the user inputs are correct");
            studentDataService.updateStudentForm(form);
            logger.debug("id = " + form.getId());
            return "redirect:/students/StudentDetails/" + form.getId();
        }
    }
}
