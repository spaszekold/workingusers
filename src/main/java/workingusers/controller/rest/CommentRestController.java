package workingusers.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workingusers.rest.Comment;
import workingusers.rest.CommentLittle;
import workingusers.service.CommentService;

import java.util.Date;
import java.util.List;

/**
 * Created by Tomek on 2015-05-04.
 */

@RequestMapping(value = "/api2/comment")
@RestController
public class CommentRestController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/add/{postid}", method = RequestMethod.POST)
    public ResponseEntity<List<Comment>> add(@PathVariable long postid, @RequestBody CommentLittle addedcomment) {
        System.out.println("/api2/comment/add/" + postid);
        System.out.println(addedcomment);


        List<Comment> res = commentService.addNew(postid,addedcomment);
        //System.out.println("result is: " + res);
        return new ResponseEntity<List<Comment>>(res,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get/{postid}", method = RequestMethod.GET)
    public List<Comment> getAllComments(@PathVariable long postid ){
        return commentService.getCommentsFromPost(postid);
    }


}
