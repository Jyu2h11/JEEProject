package com.example.univbet;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("user")
@SessionScoped
public class UserDto implements Serializable {
    private String login;
    private String password;
    private boolean connected;

    public UserDto() {
    }

    public void doLogin()
    {
        System.out.println("Connexion !");
        if(this.login.equals("JyuShII") && this.password.equals("F1n@lFantasy"))
        {
            this.connected = true;
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
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

}
