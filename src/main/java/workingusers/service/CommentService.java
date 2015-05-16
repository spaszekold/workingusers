package workingusers.service;


import org.springframework.security.core.Authentication;
import workingusers.entity.CommentEntity;
import workingusers.entity.CommentUserVote;
import workingusers.rest.Comment;
import workingusers.rest.CommentLittle;
import workingusers.rest.CommentVote;

import java.util.List;

/**
 * Created by Tomek on 2015-04-30.
 */
public interface CommentService {
    public List<Comment> getCommentsFromPost(long postid, Authentication authentication);

    public Comment addNew(long postid, CommentLittle comment, Authentication authentication);

    List<CommentUserVote> getUserVotes(long postid, Authentication authentication);

    Comment vote(CommentVote commentVote, Authentication authentication);
}
