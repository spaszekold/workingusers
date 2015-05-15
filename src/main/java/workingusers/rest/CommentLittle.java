package workingusers.rest;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Tomek on 2015-05-03.
 */

public class CommentLittle {
    public String lilname;
    public String content;
    public long parentid;

    @Override
    public String toString() {
        return "CommentLittle{" +
                "content='" + content + '\'' +
                ", lilname='" + lilname + '\'' +
                ", parentid=" + parentid +
                '}';
    }
}
