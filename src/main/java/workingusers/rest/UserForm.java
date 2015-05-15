package workingusers.rest;

/**
 * Created by Tomek on 2015-05-05.
 */
public class UserForm {

    public String email;

    public String password;

    public String nick;

    public String passwordc;

    public String getPasswordc() {
        return passwordc;
    }

    public void setPasswordc(String passwordc) {
        this.passwordc = passwordc;
    }

    public Role role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override

    public String toString() {
        return "UserForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nick='" + nick + '\'' +
                ", role=" + role +
                '}';
    }
}
