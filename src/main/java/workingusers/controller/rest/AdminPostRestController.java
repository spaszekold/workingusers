package workingusers.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import workingusers.rest.PostFull;
import workingusers.service.PostService;

/**
 * Created by Tomek on 2015-05-05.
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping(value = "/admin")
public class AdminPostRestController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/post/new", method = RequestMethod.POST)
    public boolean addNewPost(@RequestBody PostFull postFull, Authentication authentication) {
        System.out.println("otrzymalem nastepujacy post: " + postFull + "\n od usera " + authentication.getPrincipal());
        postService.addNewPost(postFull,authentication);
        return true;
    }

}
