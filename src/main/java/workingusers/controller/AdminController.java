package workingusers.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tomek on 2015-04-30.
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {



    //only admin has permission to get to admin panel
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping
    public String admin() {
        return "admin";
    }
}
