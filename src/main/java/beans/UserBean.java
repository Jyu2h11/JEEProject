package beans;

import com.example.univbet.UserDto;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@ManagedBean("userBean")
@SessionScoped
public class UserBean implements Serializable {
    private UserDto user;
    private String login;
    private String password;

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