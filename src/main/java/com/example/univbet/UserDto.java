package com.example.univbet;

import beans.ChangePageBean;
import entities.UserEntity;
import listeners.ApplicationListener;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.registry.infomodel.User;
import java.io.Serializable;
import java.util.List;

@Named("user")
@SessionScoped
public class UserDto implements Serializable {
    private String login;
    private String firstname;
    private String lastname;
    private String password;
    private boolean connected;
    private boolean isPresent;
    EntityManagerFactory emf;
    EntityManager em;
    List<UserEntity> users;
    @EJB
    ChangePageBean changePageBean;


    public UserDto() {
    }

    public String doUnlogin()
    {
        this.connected = false;
        return null;
    }

    public void doSignUp()
    {
        users = getAllUsers();
        System.out.println("Sign up !");
        if(users.isEmpty())
        {
            UserEntity message = new UserEntity();
            message.setLogin(this.login);
            message.setFirstname(this.firstname);
            message.setLastname(this.lastname);
            message.setPassword(this.password);
            em.getTransaction().begin();
            em.persist(message);
            em.getTransaction().commit();
            this.changePageBean.setPageId(0);
        }
        else
        {
            for(int i = 0; i < users.size(); i++)
            {
                if(this.login.equals(users.get(i).getLogin()))
                {
                    this.isPresent = true;
                    break;
                }
                else this.isPresent = false;

            }
            if(!this.isPresent)
            {
                UserEntity message = new UserEntity();
                message.setLogin(this.login);
                message.setFirstname(this.firstname);
                message.setLastname(this.lastname);
                message.setPassword(this.password);
                em.getTransaction().begin();
                em.persist(message);
                em.getTransaction().commit();
                this.changePageBean.setPageId(0);
            }
        }
    }

    public void doLogin() {
        users = getAllUsers();
        for(int i = 0; i < users.size(); i++) {
            if(this.login.equals(users.get(i).getLogin()) && this.password.equals(users.get(i).getPassword()))
            {
                this.login = users.get(i).getLogin();
                this.firstname = users.get(i).getFirstname();
                this.lastname = users.get(i).getLastname();
                this.connected = true;
                this.changePageBean.setPageId(0);
                break;
            }
        }


    }

    public List<UserEntity> getAllUsers() {
        emf = ApplicationListener.getEmf();
        em = emf.createEntityManager();
        Query query = em.createNamedQuery("findAllUsers",
                    UserEntity.class);
        List<UserEntity> users = query.getResultList();
        return users;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
