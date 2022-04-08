package beans;

import com.example.univbet.UserDto;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Stateless
public class UserBean{
    private UserDto user;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private boolean connected;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public UserDto getUser() {
        return user;
    }


    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PostConstruct
    private void init() {
        this.user = new UserDto();
    }

    public String validate() {
        // Validate given login and password here
        // When user seems to be valid assign it to this.user and return "/someFileForAuthorizedUsers.xhtml";
        this.login = null;
        this.password = null;

        return null;
    }
}