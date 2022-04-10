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
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private boolean connected;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

}