package workingusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import workingusers.entity.UserEntity;
import workingusers.rest.UserForm;
import workingusers.service.UserService;

/**
 * Created by Tomek on 2015-05-06.
 */


@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String getUserHomepage() {
        return "user";
    }

    @RequestMapping("/go")
    public String create(Model model) {
        model.addAttribute("userform",new UserForm());
        return "createuser";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createNewUser(@ModelAttribute("userform") UserForm userForm, Model model) {
        System.out.println(userForm);
        UserEntity e = userService.create(userForm);
        model.addAttribute("usercreated", true);
        System.out.println("powstal " + e);

        return "login";
    }

}
