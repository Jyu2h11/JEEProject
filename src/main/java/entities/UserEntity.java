package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = "findAllUsers", query = "SELECT u FROM UserEntity u")
})

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String login;

    @NotNull
    private String password;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
