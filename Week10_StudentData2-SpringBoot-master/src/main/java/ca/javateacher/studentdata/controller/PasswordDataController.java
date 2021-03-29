package ca.javateacher.studentdata.controller;

import ca.javateacher.studentdata.data.LoginDataService;
import ca.javateacher.studentdata.model.PasswordChangeForm;
import ca.javateacher.studentdata.model.PasswordChangeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/password/*")
public class PasswordDataController {

    private final Logger logger = LoggerFactory.getLogger(PasswordDataController.class);

    private LoginDataService loginDataService;

    @Autowired
    public PasswordDataController(
            @Qualifier("loginDataServiceJpaImpl") LoginDataService loginDataService) {
        this.loginDataService = loginDataService;
    }

    @InitBinder("pcform")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PasswordChangeValidator());
    }

    // a user clicks "Change password" link
    @GetMapping("/ChangePassword")
    public String changePassword(Model model) {
        logger.trace("changePassword() is called");
        model.addAttribute("pcform", new PasswordChangeForm());
        return "passwords/ChangePassword";
    }

    // a user clicks "Change Password" button in "ChangePassword.html",
    // the form submits data to "/UpdatePassword.do"
    @PostMapping("/UpdatePassword")
    public String updatePassword(
            @Validated @ModelAttribute("pcform") PasswordChangeForm pcform,
            BindingResult result) {
        logger.trace("updatePassword() is called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        if (!result.hasFieldErrors("currentPassword")) {
            if (!loginDataService.checkPassword(login, pcform.getCurrentPassword().trim())) {
                result.rejectValue("currentPassword", "currentPassword.wrong");
                logger.trace("the entered current password is wrong");
            }
        }

        if (result.hasErrors()) {
            logger.trace("input validation errors");
            return "passwords/ChangePassword";
        } else {
            loginDataService.updatePassword(login, pcform.getNewPassword1().trim());
            logger.trace("the password is updated");
            return "passwords/PasswordChanged";
        }
    }

}
