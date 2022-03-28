package com.example.univbet;

import entities.UserEntity;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.registry.infomodel.User;
import java.io.Serializable;

@Named("user")
@SessionScoped
public class UserDto implements Serializable {
    private String login;
    private String password;
    private boolean connected;


    public UserDto() {
    }

    public String doLogin() {
        if (this.login.equals("Admin") && this.password.equals("Password0!")) {
            this.connected = true;
        }
        /*
        Peut être à faire autre part
         */
        UserEntity message = new UserEntity();
        message.setLogin(this.login);
        message.setPassword(this.password);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return null;
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
