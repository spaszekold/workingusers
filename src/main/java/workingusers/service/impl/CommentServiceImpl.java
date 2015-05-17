package workingusers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import workingusers.entity.*;
import workingusers.repository.CommentRepository;
import workingusers.repository.CommentUserVoteRepository;
import workingusers.repository.PostRepository;
import workingusers.repository.UserRepository;
import workingusers.rest.Comment;
import workingusers.rest.CommentLittle;
import workingusers.rest.CommentVote;
import workingusers.service.CommentService;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by Tomek on 2015-04-30.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentUserVoteRepository commentUserVoteRepository;


    /**
     * returns all comments from given post
     */
    @Override
    public List<Comment> getCommentsFromPost(long postid, Authentication authentication) {
        PostEntity p = postRepository.findOneByPostid(postid);
        List<CommentEntity> temp = commentRepository.findAllByPostidOrderByCreatedDesc(p);
        List<Comment> result = new ArrayList();
        List<CommentUserVote> votedPosts = null;

        if (authentication != null) {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            Optional<UserEntity> userEntity = userRepository.findOneByEmail(user.getUsername());
            if (userEntity.isPresent()) {
                votedPosts = commentUserVoteRepository.findAllByCommentUserVoteIdUserAndPostid(userEntity.get(),postid);
                System.out.println(votedPosts);
            }
        }

        //wrapping CommentEntity into lighter Comment
        for (CommentEntity c : temp) {
            long parentid = -1;
            if (c.getParent() != null)
                parentid = c.getParent().getCommentid();


            UserEntity author = userRepository.findOneByNick(c.getUserid().getNick());

            result.add(new Comment(c.getContent(), c.getCreated(), c.getCommentid(), c.getLilname(), c.getDepth(),parentid, author.getNick(), c.getScore(),containsComment(c,votedPosts)));
        }

        //return sorted comment list
        return arrangeList(result);

    }

    private int containsComment(CommentEntity commentEntity, List<CommentUserVote> votes) {
        Iterator<CommentUserVote> voteIterator = votes.iterator();
        while (voteIterator.hasNext()) {
            CommentUserVote next = voteIterator.next();
            if (next.getComment().equals(commentEntity))
                return next.getPoints();
        }
        return 0;
    }

    @Override
    public Comment addNew(long postid, CommentLittle comment, Authentication authentication) {
        System.out.println("comment service, adding comment to " + postid);
        PostEntity p = postRepository.findOneByPostid(postid);
        CommentEntity parent = commentRepository.findOneByCommentid(comment.parentid);


        CommentEntity newComment = new CommentEntity(comment.content,new Date(), comment.lilname, p, parent);




        UserDetails user = (UserDetails) authentication.getPrincipal();
        Optional<UserEntity> userEntity = userRepository.findOneByEmail(user.getUsername());


        String usernick = "";
        if (userEntity.isPresent()) {
            newComment.setUserid(userEntity.get());
            usernick = userEntity.get().getNick();
        }

        commentRepository.save(newComment);

        long parentid = -1;
        if (newComment.getParent() != null)
            parentid = newComment.getParent().getCommentid();


        Comment result = new Comment(newComment.getContent(), newComment.getCreated(), newComment.getCommentid(), newComment.getLilname(), newComment.getDepth(), parentid, usernick, 0, 0 );
        System.out.println("dodano" + result);
        return result;
    }

    @Override
    public Comment vote(CommentVote commentVote, Authentication authentication) {
        CommentEntity votedComment = commentRepository.findOneByCommentid(commentVote.commentid);
        UserDetails user = (UserDetails) authentication.getPrincipal();
        if (user == null) {
            System.out.println("authentication failed");
            return null;

        }
        Optional<UserEntity> userEntity = userRepository.findOneByEmail(user.getUsername());
        CommentUserVoteId commentUserVoteId = new CommentUserVoteId(votedComment,userEntity.get());
        CommentUserVote alreadyVoted = commentUserVoteRepository.findOneByCommentUserVoteId(commentUserVoteId);
        if (alreadyVoted == null) {
            System.out.println("wyglada na to, ze bedzie male glosowanko");

            int currentScore = votedComment.getScore();
            CommentUserVote commentUserVote = new CommentUserVote();
            commentUserVote.setComment(votedComment);
            commentUserVote.setUser(userEntity.get());
            commentUserVote.setPoints(commentVote.points);
            commentUserVote.setPostid(commentVote.postid);
            votedComment.getCommentUserVote().add(commentUserVote);
            votedComment.setScore(currentScore + commentVote.points);
            commentRepository.save(votedComment);

            long parentid = -1;
            if (votedComment.getParent() != null)
                parentid = votedComment.getParent().getCommentid();

            return new Comment(votedComment.getContent(),votedComment.getCreated(),votedComment.getCommentid(),votedComment.getLilname(),
                                votedComment.getDepth(),parentid,votedComment.getUserid().getNick(),votedComment.getScore(),commentVote.points);
        }
        else {
            int currentScore = votedComment.getScore();
            votedComment.getCommentUserVote().remove(alreadyVoted);
            userEntity.get().getCommentUserVotes().remove(alreadyVoted);
            System.out.println(userEntity.get().getCommentUserVotes());
            int votedScore = alreadyVoted.getPoints();
            if (votedScore > 0)
                votedScore *= -1;

            votedComment.setScore(currentScore - alreadyVoted.getPoints());
            commentRepository.save(votedComment);
            userRepository.save(userEntity.get());
            commentUserVoteRepository.delete(alreadyVoted);
            long parentid = -1;
            if (votedComment.getParent() != null)
                parentid = votedComment.getParent().getCommentid();

            return new Comment(votedComment.getContent(),votedComment.getCreated(),votedComment.getCommentid(),votedComment.getLilname(),
                    votedComment.getDepth(),parentid,votedComment.getUserid().getNick(),votedComment.getScore(),commentVote.points);
        }

    }


    /**
     * used to sort a list to display the comments correctly.
     * method takes the commentList and returns an arranged list of nested comments
     *
     * the algorithm handles hundreds(500+) of comments instantly
     * it is important that list of comments must be sorted beforehand descending by created date(or ascending by comment score)
     *
     * pseudocode:
     * 1) put all comments who have no parents in the result list and also calculate maximum depth of all comments
     * 2) set currentdepth to 1
     * 3) while we havent reached the maximum depth:
     *      a) for each comment within current depth level
     *          I) find parent's index in the result list
     *          II) put comment right after the parent(parentindex +1) in the result list
     *          III) remove comment from remaining list
     *      b) increment currentdepth by 1
     * 4) return result list
     *
     *
     * we end up like this:
     * #COMMENT 1
     *      #COMMENT 3 WHICH RESPONDS TO COMMENT 1
     *      #COMMENT 4 WHICH RESPONDS TO COMMENT 1
     *          #COMMENT 5 WHICH RESPONDS TO COMMENT 4
     * #COMMENT 2
     *      #COMMENT 6 WHICH RESPONDS TO COMMENT 2
     * and so on
     *
     * frontend work is fairly easy, just display each comment with margin-left equal to depth * 30(or any fixed value)
     */
    private List<Comment> arrangeList(List<Comment> commentList) {

        List<Comment> result = new ArrayList<Comment>();
        List<Comment> remainingComments = commentList;
        Iterator<Comment> commentIter = remainingComments.iterator();
        int maxDepth = 0;



        //calculate maximum depth of comments
        //and add no-parent comments to result list
        while (commentIter.hasNext()){
            Comment comment = commentIter.next();
            if (comment.getDepth() > maxDepth)
                maxDepth = comment.getDepth();
            if (comment.getDepth() == 0) {
                result.add(0,comment);
                commentIter.remove();
            }
        }

        if (maxDepth != 0) {
            //while we havent reached maximum depth
            int currentDepth = 1;
            do {
                //get iterator from remainingComments
                commentIter = remainingComments.iterator();
                //iterate over entire comment list
                while (commentIter.hasNext()) {
                    //grab current comment
                    Comment comment = commentIter.next();
                    //if comment's depth equals currently looked-on depth
                    if (comment.getDepth() == currentDepth) {
                        //get parent index
                        int parentindex = getIndexOf(result, comment.getParentid());
                        //put it in result after the parent
                        result.add(parentindex + 1, comment);
                        //remove from remainingComments list
                        commentIter.remove();
                    }

                }
            } while (currentDepth++ != maxDepth);
        }


        return result;
    }


    //helper method, returns index of Comment on list with given id
    public int getIndexOf(List<Comment> list, long pid) {
        Iterator<Comment> iterator = list.iterator();

        int index = 0;
        while (iterator.hasNext()) {
            Comment next = iterator.next();
            if (next.id == pid) {
               System.out.println("wychodzimy z loopa z indexem " + index);
                break;
            }
            index++;
        }



        return index;
    }



}
