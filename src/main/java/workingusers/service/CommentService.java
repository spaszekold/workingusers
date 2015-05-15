package workingusers.service;


import workingusers.entity.CommentEntity;
import workingusers.rest.Comment;
import workingusers.rest.CommentLittle;

import java.util.List;

/**
 * Created by Tomek on 2015-04-30.
 */
public interface CommentService {
    public List<Comment> getCommentsFromPost(long postid);

    public List<Comment> addNew(long postid, CommentLittle comment);
}
