package ca.sheridancollege.hoangjam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping({"/", "InputAnswer.do"})
    public String inputAnswer(HttpSession session) {
        MulObject mulObj = new MulObject();
        session.setAttribute("mulObj", mulObj);

        return "InputAnswer";
    }

    @GetMapping("CheckAnswer.do")
    public ModelAndView checkAnswer(HttpSession session, @RequestParam int userAnswer) {

        // casting it to the correct object to get access
        MulObject mulObj = (MulObject) session.getAttribute("mulObj");
        ModelAndView mv;

        if (userAnswer == mulObj.getResult()) {
            mv = new ModelAndView("CorrectAnswer", "userAnswer", userAnswer);
        }
        else{
            mv = new ModelAndView("WrongAnswer", "userAnswer", userAnswer);
        }
        return mv;
    }

}
