package workingusers.rest;

import java.util.Date;

/**
 * Created by Tomek on 2015-05-01.
 */
public class Comment {

    public long id;

    public String content;
    public String lilname;
    public Date created;
    public int depth;

    public int vote;


    public int isHasVoted() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long parentid;
    public String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public long getParentid() {
        return parentid;
    }

    public void setParentid(long parentid) {
        this.parentid = parentid;
    }

    public Comment(String content, Date created, long id, String lilname,int depth,  long parentid, String author, int score, int hasVoted) {
        this.content = content;
        this.created = created;
        this.depth = depth;
        this.id = id;
        this.lilname = lilname;
        this.author = author;
        this.parentid = parentid;
        this.score = score;
        this.vote = hasVoted;
    }



    public String getContent() {

        return content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "author='" + author + '\'' +
                ", id=" + id +
                ", content='" + content + '\'' +
                ", lilname='" + lilname + '\'' +
                ", created=" + created +
                ", depth=" + depth +
                ", parentid=" + parentid +
                '}';
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
}
