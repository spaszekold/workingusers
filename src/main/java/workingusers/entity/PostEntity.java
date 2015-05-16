package workingusers.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tomek on 2015-04-30.
 */

@Entity
@Table(name = "posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "postid")
    private long postid;


    @Column(length = 35, name = "lilname")
    private String lilname;
    @Column(length = 200, name = "fullname")
    private String fullname;
    @Column(length = 10000, name = "content")
    private String content;


    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-YYYY hh:mm")
    @Column(name = "created")
    private Date created;

    public UserEntity getUserid() {
        return userid;
    }

    public void setUserid(UserEntity userid) {
        this.userid = userid;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private UserEntity userid;

    @OneToMany(mappedBy = "postid", cascade = CascadeType.ALL)
    private Set<CommentEntity> comments = new HashSet<CommentEntity>();

    public Set<CommentEntity> getComments() {
        return comments;
    }


    @Access(value = AccessType.PROPERTY)
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(name="POSTS_TAGS",
                joinColumns = {@JoinColumn(name="postid", referencedColumnName = "postid")},
                inverseJoinColumns = {@JoinColumn(name="tagid", referencedColumnName = "id")})
    private Set<TagEntity> tags = new HashSet<TagEntity>();

    public Set<TagEntity> getTags() {
        return tags;
    }

    public void setTags(Set<TagEntity> tags) {
        this.tags = tags;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    public PostEntity() {

    }

    public PostEntity(String lilname, String content, Date created, String fullname, Set<TagEntity> tags) {
        this.lilname = lilname;
        this.content = content;
        this.created = created;
        this.fullname = fullname;
        this.tags = tags;
    }

    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }

    public String getLilname() {
        return lilname;
    }

    public void setLilname(String lilname) {
        this.lilname = lilname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    @Override
    public String toString() {
        return "PostEntity{" +
                "content='" + content + '\'' +
                ", postid=" + postid +
                ", lilname='" + lilname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", created=" + created +
                '}';
    }
}
