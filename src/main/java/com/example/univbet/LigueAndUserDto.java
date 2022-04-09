package com.example.univbet;

import beans.LigueBean;
import beans.UserBean;
import entities.LigueEntity;
import entities.UserEntity;
import entities.UserHasLigueEntity;
import listeners.ApplicationListener;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionListener;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.xml.registry.infomodel.User;
import java.io.Serializable;
import java.util.List;

@Named("ligue")
@SessionScoped
public class LigueAndUserDto implements Serializable {

    EntityManagerFactory emf;
    EntityManager em;
    private boolean isPresent;
    private boolean userPresentInLigue;


    @EJB
    UserBean currentUser;
    @EJB
    LigueBean currentLigue;

    public void addLigue() {
        this.emf = this.currentLigue.getEmf();
        this.em = this.currentLigue.getEm();
        if(this.currentLigue.getLigues().isEmpty()) {
            createLigueInDatabase();
        }
        else
        {
            for(int i = 0; i < this.currentLigue.getLigues().size(); i++)
            {
                    if(getNomLigue().equals(this.currentLigue.getLigues().get(i).getNomLigue()))
                    {
                        this.isPresent = true;
                        break;
                    }
                    else this.isPresent = false;
                }

            if(!this.isPresent)
            {
               createLigueInDatabase();
            }
        }
        linkUserAndLigue();
        this.currentLigue.setUserPresentInLigue();

    }

    public void addLigue2()
    {
        this.emf = this.currentLigue.getEmf();
        this.em = this.currentLigue.getEm();
        if(this.currentLigue.getLigues().isEmpty()) {
            createLigueInDatabase();
        }
        else
        {
            for(int i = 0; i < this.currentLigue.getLigues().size(); i++)
            {
                if(getNomLigue2().equals(this.currentLigue.getLigues().get(i).getNomLigue()))
                {
                    this.isPresent = true;
                    break;
                }
                else this.isPresent = false;
            }

            if(!this.isPresent)
            {
                createLigueInDatabase();
            }
        }
        linkUserAndLigue();
        this.currentLigue.setUserPresentInLigue();

    }


    private void linkUserAndLigue() {
        if(this.currentLigue.getAllUserHasLigues().isEmpty())
        {
            UserHasLigueEntity message = new UserHasLigueEntity();
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstname(this.currentUser.getFirstName());
            userEntity.setLastname(this.currentUser.getLastName());
            userEntity.setLogin(this.currentUser.getLogin());
            userEntity.setPassword(this.currentUser.getPassword());
            userEntity.setId(this.currentLigue.getCurrentUserId().get(0));
            LigueEntity ligueEntity = new LigueEntity();
            if(getNomLigue().isEmpty()) ligueEntity.setNomLigue(getNomLigue2());
            else ligueEntity.setNomLigue(getNomLigue());
            ligueEntity.setId(getCurrentLigueId().get(0));
            message.setUserEntity(userEntity);
            message.setLigueEntity(ligueEntity);
            em.getTransaction().begin();
            em.persist(message);
            em.getTransaction().commit();
        }
        else {
            if (this.currentLigue.isUserPresentInLigue()) {
                UserHasLigueEntity message = new UserHasLigueEntity();
                UserEntity userEntity = new UserEntity();
                userEntity.setFirstname(this.currentUser.getFirstName());
                userEntity.setLastname(this.currentUser.getLastName());
                userEntity.setLogin(this.currentUser.getLogin());
                userEntity.setPassword(this.currentUser.getPassword());
                userEntity.setId(this.currentLigue.getCurrentUserId().get(0));
                LigueEntity ligueEntity = new LigueEntity();
                if(getNomLigue().isEmpty()) ligueEntity.setNomLigue(getNomLigue2());
                else ligueEntity.setNomLigue(getNomLigue());
                ligueEntity.setId(getCurrentLigueId().get(0));
                message.setUserEntity(userEntity);
                message.setLigueEntity(ligueEntity);
                em.getTransaction().begin();
                em.persist(message);
                em.getTransaction().commit();
            }
        }
    }



    private void createLigueInDatabase() {
        LigueEntity message = new LigueEntity();
        if(getNomLigue().isEmpty())
        {
            message.setNomLigue(getNomLigue2());
        }
        else message.setNomLigue(getNomLigue());

        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
    }

    public String getNomLigue() {
        return this.currentLigue.getNomLigue();
    }

    public String getNomLigue2()
    {
        return this.currentLigue.getNomLigue2();
    }

    public List<String> getAllLiguesName()
    {
       return this.currentLigue.getAllLiguesName();
    }

    public List<Long> getCurrentLigueId()
    {
        Query query = null;
        if(getNomLigue().isEmpty())  query = em.createQuery("SELECT l.id FROM LigueEntity l WHERE l.nomLigue = '" + this.currentLigue.getNomLigue2() + "'", Long.class);
        else query = em.createQuery("SELECT l.id FROM LigueEntity l WHERE l.nomLigue = '" + this.currentLigue.getNomLigue() + "'", Long.class);
        List<Long> ligueId = query.getResultList();
        return ligueId;
    }

    public String getLigueNameOfUser()
    {
        return this.currentLigue.getLigueNameOfUserList().get(0);
    }


    public void setUserPresentInLigue() {
        this.currentLigue.setUserPresentInLigue();
    }

    public boolean isUserPresentInLigue() {
        return this.currentLigue.isUserPresentInLigue();
    }



    public void setNomLigue(String nomLigue) {
        this.currentLigue.setNomLigue(nomLigue);
    }

    public void setNomLigue2(String nomLigue2) {
        this.currentLigue.setNomLigue2(nomLigue2);

    }

}
