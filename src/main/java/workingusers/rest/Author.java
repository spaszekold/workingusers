package workingusers.rest;

/**
 * Created by Tomek on 2015-05-06.
 */
public class Author {
    public Author() {
    }

    private String nickname;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author(long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
