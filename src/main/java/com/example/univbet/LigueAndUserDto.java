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
    List<LigueEntity> ligues;
    private boolean isPresent;
    private boolean userPresentInLigue;


    @EJB
    UserBean currentUser;
    @EJB
    LigueBean currentLigue;

    public void addLigue() {

        System.out.println(verifyUserHasLiguePresent());
        if(ligues.isEmpty()) {
            createLigueInDatabase();
        }
        else
        {
            for(int i = 0; i < ligues.size(); i++)
            {
                if(this.currentLigue.getNomLigue().equals(this.ligues.get(i).getNomLigue()))
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

    }


    private void linkUserAndLigue() {
        if(getAllUserHasLigues().isEmpty())
        {
            UserHasLigueEntity message = new UserHasLigueEntity();
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstname(this.currentUser.getFirstName());
            userEntity.setLastname(this.currentUser.getLastName());
            userEntity.setLogin(this.currentUser.getLogin());
            userEntity.setPassword(this.currentUser.getPassword());
            userEntity.setId(getCurrentUserId().get(0));
            LigueEntity ligueEntity = new LigueEntity();
            ligueEntity.setNomLigue(this.currentLigue.getNomLigue());
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
                userEntity.setId(getCurrentUserId().get(0));
                LigueEntity ligueEntity = new LigueEntity();
                ligueEntity.setNomLigue(this.currentLigue.getNomLigue());
                ligueEntity.setId(getCurrentLigueId().get(0));
                message.setUserEntity(userEntity);
                message.setLigueEntity(ligueEntity);
                em.getTransaction().begin();
                em.persist(message);
                em.getTransaction().commit();
            }
        }
    }

    public boolean verifyUserHasLiguePresent()
    {
        System.out.println(getAllUserHasLigues());
        for(int i = 0; i < getAllUserHasLigues().size(); i++)
        {
            if(this.currentUser.getLogin().equals(getAllUserHasLigues().get(i).getLogin()))
            {
                this.currentLigue.setUserPresentInLigue(true);
                break;
            }
            else this.currentLigue.setUserPresentInLigue(false);
        }
        return this.currentLigue.isUserPresentInLigue();
    }

    private void createLigueInDatabase() {
        LigueEntity message = new LigueEntity();
        message.setNomLigue(getNomLigue());
        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
    }

    public String getNomLigue() {
        return this.currentLigue.getNomLigue();
    }

    public List<Long> getCurrentUserId()
    {
        Query query = em.createQuery("SELECT u.id FROM UserEntity  u WHERE u.login = '" + this.currentUser.getLogin() + "'",
                Long.class);
        List<Long> userId = query.getResultList();
        return userId;
    }

    public List<Long> getCurrentLigueId()
    {
        Query query = em.createQuery("SELECT l.id FROM LigueEntity l WHERE l.nomLigue = '" + this.currentLigue.getNomLigue() + "'",
                Long.class);
        List<Long> ligueId = query.getResultList();
        return ligueId;
    }
    public List<LigueEntity> getAllLigues()
    {
        Query query = em.createNamedQuery("findAllLigues",
                LigueEntity.class);
        List<LigueEntity> ligues = query.getResultList();
        return ligues;
    }

    public List<UserEntity> getAllUserHasLigues()
    {

        Query query = em.createQuery("SELECT ul.userEntity FROM UserHasLigueEntity ul WHERE ul.userEntity = '" + getCurrentUserId().get(0) + "'",
                UserEntity.class);
        List<UserEntity> userHasLigueId = query.getResultList();
        return userHasLigueId;
    }

    public void initializeBDD()
    {
        emf = ApplicationListener.getEmf();
        em = emf.createEntityManager();
    }


    public boolean isUserPresentInLigue() {
        return this.currentLigue.isUserPresentInLigue();
    }

    public void setUserPresentInLigue() {
        initializeBDD();
        ligues = getAllLigues();
        this.currentLigue.setNomLigue("test");
        this.currentLigue.setUserPresentInLigue(verifyUserHasLiguePresent());
    }

    public void setNomLigue(String nomLigue) {
        this.currentLigue.setNomLigue(nomLigue);
    }

}
