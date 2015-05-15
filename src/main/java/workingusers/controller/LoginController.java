package workingusers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import workingusers.rest.UserForm;

/**
 * Created by Tomek on 2015-04-30.
 */
@Controller
public class LoginController {


    @RequestMapping(value="/login")
    public String login(Model model) {
        model.addAttribute("userform",new UserForm());
        return "login";
    }

}
