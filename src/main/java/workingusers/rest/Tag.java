package workingusers.rest;

/**
 * Created by Tomek on 2015-05-01.
 */
public class Tag {
    private long id;
    private String name;

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tag() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag(long id, String name) {

        this.id = id;
        this.name = name;
    }
}
