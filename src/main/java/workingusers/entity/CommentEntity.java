package workingusers.entity;

import workingusers.rest.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Tomek on 2015-04-30.
 */

@Entity
@Table(name = "COMMENTS")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "postid")
    private PostEntity postid;

    @OneToOne
    @JoinColumn(name = "commentid")
    private CommentEntity parent;

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
        return "CommentEntity{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", lilname='" + lilname + '\'' +
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

    private String lilname;
    private String content;
    private Date created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
