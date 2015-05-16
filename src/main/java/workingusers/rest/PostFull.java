package workingusers.rest;

import java.util.Date;
import java.util.List;

/**
 * Created by Tomek on 2015-04-30.
 */
public class PostFull {

    private long postid;
    private String lilname;
    private String fullname;
    private String content;
    private Date date;
    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
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

    public PostFull() {
    }

    @Override
    public String toString() {
        return "PostFull{" +
                "content='" + content + '\'' +
                ", postid=" + postid +
                ", lilname='" + lilname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", date=" + date +
                ", tags=" + tags +
                '}';
    }

    public PostFull(String content, Date date, String fullname, long id, String lilname) {

        this.content = content;
        this.date = date;
        this.fullname = fullname;
        this.postid = id;
        this.lilname = lilname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
