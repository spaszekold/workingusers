package workingusers.entity;

import javax.persistence.*;

/**
 * Created by Tomek on 2015-05-16.
 */

@Entity
@Table(name = "COMMENT_USER_VOTES")
@AssociationOverrides({
        @AssociationOverride(name = "commentUserVoteId.comment", joinColumns = @JoinColumn(name = "commentid")),
        @AssociationOverride(name ="commentUserVoteId.user", joinColumns = @JoinColumn(name = "userid"))
})
public class CommentUserVote {

    @EmbeddedId
    private CommentUserVoteId commentUserVoteId = new CommentUserVoteId();

    @Column(name = "points")
    private int points;

    public CommentEntity getComment() {
        return commentUserVoteId.getComment();
    }

    public void setComment(CommentEntity commentEntity) {
        commentUserVoteId.setComment(commentEntity);
    }

    public UserEntity getUser() {
        return commentUserVoteId.getUser();
    }

    public void setUser(UserEntity userEntity) {
        commentUserVoteId.setUser(userEntity);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public CommentUserVoteId getCommentUserVoteId() {

        return commentUserVoteId;
    }

    public void setCommentUserVoteId(CommentUserVoteId commentUserVoteId) {
        this.commentUserVoteId = commentUserVoteId;
    }
}
