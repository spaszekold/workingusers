package workingusers.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import workingusers.entity.CommentUserVote;
import workingusers.rest.Comment;
import workingusers.rest.CommentLittle;
import workingusers.rest.CommentVote;
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

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public ResponseEntity<Comment> vote(@RequestBody CommentVote commentVote, Authentication authentication) {
        return new ResponseEntity<Comment>(commentService.vote(commentVote, authentication),HttpStatus.OK);
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/add/{postid}", method = RequestMethod.POST)
    public ResponseEntity<Comment> add(@PathVariable long postid, @RequestBody CommentLittle addedcomment, Authentication authentication) {
        System.out.println("/api2/comment/add/" + postid);
        System.out.println(addedcomment);


        Comment res = commentService.addNew(postid,addedcomment,authentication);
        //System.out.println("result is: " + res);
        return new ResponseEntity<Comment>(res,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get/{postid}", method = RequestMethod.GET)
    public List<Comment> getAllComments(@PathVariable long postid, Authentication authentication){
        return commentService.getCommentsFromPost(postid, authentication);
    }

    @RequestMapping(value = "/get/votes/{postid}", method = RequestMethod.GET)
    public List<CommentUserVote> getYourVotes(@PathVariable long postid, Authentication authentication) {
        return commentService.getUserVotes(postid, authentication);
    }

}
