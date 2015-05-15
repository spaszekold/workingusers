package workingusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import workingusers.entity.PostEntity;
import workingusers.service.PostService;
import workingusers.util.PageWrapper;

/**
 * Created by Tomek on 2015-05-07.
 */
@Controller
public class ViewController {

    @Autowired
    private PostService postService;


    @RequestMapping(value = "/")
    public String index() {

        return "redirect:/post/page/1";
    }

}
