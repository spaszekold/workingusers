package workingusers.controller.advice;

/**
 * Created by Tomek on 2015-05-05.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


/*
to be used in future
add modelAttribute currentuser equal to user details provided by Authentication
 */
@ControllerAdvice
public class UserControllerAdvice {

    @ModelAttribute("currentuser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }


}
