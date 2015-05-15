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
    public long parentid;

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

    public Comment(String content, Date created, long id, String lilname,int depth,  long parentid) {
        this.content = content;
        this.created = created;
        this.depth = depth;
        this.id = id;
        this.lilname = lilname;
        this.parentid = parentid;
    }



    public String getContent() {

        return content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", lilname='" + lilname + '\'' +
                ", created=" + created +
                ", depth=" + depth +
                ", parentid=" + parentid +
                "}\n";
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
