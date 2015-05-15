package workingusers.rest;

import workingusers.entity.TagEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Tomek on 2015-04-30.
 */
public class PostFront {

    private long id;
    private String fullname;
    private String lilname;
    private Date created;
    private Set<Tag> tags;
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public PostFront() {
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public PostFront(long id, String fullname,  String lilname, Date created, Set<Tag> tags, Author author ) {

        this.created = created;
        this.fullname = fullname;
        this.id = id;
        this.author = author;
        this.lilname = lilname;
        this.tags = tags;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLilname() {
        return lilname;
    }

    public void setLilname(String lilname) {
        this.lilname = lilname;
    }
}
