package workingusers.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workingusers.rest.Comment;
import workingusers.rest.CommentLittle;
import workingusers.rest.PostFront;
import workingusers.service.CommentService;
import workingusers.service.PostService;

import java.util.Date;
import java.util.List;

/**
 * Created by Tomek on 2015-04-30.
 */
@RestController
@RequestMapping(value = "/api")
public class ViewRestController {


    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;



    @RequestMapping(value = "/getLatest")
    public @ResponseBody List<PostFront> getAll() {
        System.out.println("/api/getLatest");
      //  return postService.getFront();
        return null;
    }





}
