package workingusers.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tomek on 2015-05-01.
 */

@Entity
@Table(name = "tags")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "tagname")
    private String tagname;


    @ManyToMany(targetEntity = PostEntity.class, mappedBy = "tags")
    @JsonBackReference
    private Set<PostEntity> posts = new HashSet<PostEntity>();

    public Set<PostEntity> getPosts() {
      return posts;
    }

    public void setPosts(Set<PostEntity> posts) {
        this.posts = posts;
    }



    public TagEntity(Set<PostEntity> posts, String tagname) {
        this.posts = posts;
        this.tagname = tagname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public TagEntity() {
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
}
