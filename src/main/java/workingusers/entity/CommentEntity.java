package workingusers.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Tomek on 2015-04-30.
 */

@Entity
@Table(name = "COMMENTS")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentid;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "postid")
    private PostEntity postid;

    @OneToOne
    @JoinColumn(name = "parentid")
    private CommentEntity parent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private UserEntity userid;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "commentUserVoteId.comment")
    private Set<CommentUserVote> commentUserVote;

    public Set<CommentUserVote> getCommentUserVote() {
        return commentUserVote;
    }

    public void setCommentUserVote(Set<CommentUserVote> commentUserVote) {
        this.commentUserVote = commentUserVote;
    }

    private int score;

    public PostEntity getPostid() {
        return postid;
    }

    public void setPostid(PostEntity postid) {
        this.postid = postid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public UserEntity getUserid() {
        return userid;
    }

    public void setUserid(UserEntity userid) {
        this.userid = userid;
    }

    private int depth;

    public PostEntity getPost() {
        return postid;
    }

    public void setPost(PostEntity post) {
        this.postid = post;
    }

    public CommentEntity() {

    }

    @Override
    public String toString() {

        if (parent == null)
            return "CommentEntity{" +
                "commentid=" + commentid +
                ", parent=null " +
                ", userid=" + userid +
                ", commentUserVote=" + commentUserVote +
                ", score=" + score +
                ", depth=" + depth +
                ", lilname='" + lilname + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                '}';
        else
            return "CommentEntity{" +
                    "commentid=" + commentid +
                    ", parent= " + parent.getCommentid() +
                    ", userid=" + userid +
                    ", commentUserVote=" + commentUserVote +
                    ", score=" + score +
                    ", depth=" + depth +
                    ", lilname='" + lilname + '\'' +
                    ", content='" + content + '\'' +
                    ", created=" + created +
                    '}';
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public CommentEntity getParent() {
        return parent;
    }

    public void setParent(CommentEntity parent) {
        this.parent = parent;
    }

    public CommentEntity(String content, Date created, String lilname, PostEntity postid, CommentEntity parent) {
        this.parent = parent;
        this.content = content;

        this.created = created;
        this.lilname = lilname;
        this.postid = postid;
        if (parent == null)
            this.depth = 0;
        else
            this.depth = 1 + parent.getDepth();
    }

    @Column(length = 100)
    private String lilname;

    @Column(length = 500)
    private String content;
    private Date created;

    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
    }

    public String getLilname() {
        return lilname;
    }

    public void setLilname(String lilname) {
        this.lilname = lilname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
