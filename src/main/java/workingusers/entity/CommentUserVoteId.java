package workingusers.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Created by Tomek on 2015-05-16.
 */
@Embeddable
public class CommentUserVoteId implements java.io.Serializable {

    @ManyToOne
    @JsonManagedReference
    private CommentEntity comment;

    @Override
    public String toString() {
        return "CommentUserVoteId{" +
                "comment=" + comment.getCommentid() +
                ", user=" + user.getNick() +
                '}';
    }

    @ManyToOne
    @JsonManagedReference
    private UserEntity user;

    public CommentUserVoteId(CommentEntity comment, UserEntity user) {
        this.comment = comment;
        this.user = user;
    }

    public CommentUserVoteId() {

    }

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
