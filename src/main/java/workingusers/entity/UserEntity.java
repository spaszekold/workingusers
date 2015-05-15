package workingusers.entity;

import workingusers.rest.Role;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tomek on 2015-05-05.
 */
@Entity
@Table(name = "users222")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nick", nullable = false, unique = true)
    private String nick;

    @Column(name = "hash")
    private String passwordHash;

    public Set<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostEntity> posts) {
        this.posts = posts;
    }

    @OneToMany(mappedBy = "userid", cascade = CascadeType.ALL)
    private Set<PostEntity> posts;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity() {
    }

    public UserEntity(String email, String nick, String passwordHash, Role role) {

        this.email = email;
        this.nick = nick;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "email='" + email + '\'' +
                ", id=" + id +
                ", nick='" + nick + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", role=" + role +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
