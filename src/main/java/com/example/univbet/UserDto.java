package com.example.univbet;

import beans.ChangePageBean;
import beans.LigueBean;
import beans.UserBean;
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
    private boolean isPresent;
    EntityManagerFactory emf;
    EntityManager em;
    List<UserEntity> users;
    @EJB
    ChangePageBean changePageBean;
    @EJB
    UserBean userBean;
    @EJB
    LigueBean ligueBean;


    public UserDto() {
    }

    public String doUnlogin()
    {
        this.userBean.setConnected(false);
        this.changePageBean.setPageId(0);
        this.ligueBean.setUserPresentInLigue(false);
        return null;
    }

    public void doSignUp()
    {
        users = getAllUsers();
        if(users.isEmpty())
        {
            UserEntity message = new UserEntity();
            message.setLogin(this.userBean.getLogin());
            message.setFirstname(this.userBean.getFirstName());
            message.setLastname(this.userBean.getLastName());
            message.setPassword(this.userBean.getPassword());
            em.getTransaction().begin();
            em.persist(message);
            em.getTransaction().commit();
            this.changePageBean.setPageId(0);
        }
        else
        {
            for(int i = 0; i < users.size(); i++)
            {
                if(this.userBean.getLogin().equals(users.get(i).getLogin()))
                {
                    this.isPresent = true;
                    break;
                }
                else this.isPresent = false;

            }
            if(!this.isPresent)
            {
                UserEntity message = new UserEntity();
                message.setLogin(this.userBean.getLogin());
                message.setFirstname(this.userBean.getFirstName());
                message.setLastname(this.userBean.getLastName());
                message.setPassword(this.userBean.getPassword());
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
            if(this.userBean.getLogin().equals(users.get(i).getLogin()) && this.userBean.getPassword().equals(users.get(i).getPassword()))
            {
                this.userBean.setLogin(users.get(i).getLogin());
                this.userBean.setFirstName(users.get(i).getFirstname());
                this.userBean.setLastName(users.get(i).getLastname());
                this.userBean.setConnected(true);
                this.ligueBean.verifyUserHasLiguePresent();
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
        return this.userBean.isConnected();
    }

    public void setConnected(boolean connected) {
        this.userBean.setConnected(connected);
    }

    public String getLogin() {
        return this.userBean.getLogin();
    }

    public void setLogin(String login) {
        this.userBean.setLogin(login);
    }

    public String getPassword() {
        return this.userBean.getPassword();
    }

    public void setPassword(String password) {
        this.userBean.setPassword(password);
    }

    public String getFirstname() {
        return this.userBean.getFirstName();
    }

    public void setFirstname(String firstname) {
        this.userBean.setFirstName(firstname);
    }

    public String getLastname() {
        return this.userBean.getLastName();
    }

    public void setLastname(String lastname) {
        this.userBean.setLastName(lastname);
    }

}
