package com.example.univbet;

import beans.EquipeBean;
import entities.EquipeEntity;
import entities.UserEntity;
import listeners.ApplicationListener;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Named("equipe")
@SessionScoped
public class EquipeDto implements Serializable {

    List<EquipeEntity> equipes;
    private boolean isPresent;
    @EJB
    EquipeBean equipeBean;
    EntityManagerFactory emf;
    EntityManager em;

    public void createEquipe()
    {
        this.em = this.equipeBean.getEm();
        this.emf = this.equipeBean.getEmf();
        equipes = getAllEquipes();
        if(equipes.isEmpty())
        {
            EquipeEntity message = new EquipeEntity();
            message.setNomEquipe(this.equipeBean.getNomEquipe());
            em.getTransaction().begin();
            em.persist(message);
            em.getTransaction().commit();
            this.equipeBean.setEquipesName(this.equipeBean.getAllEquipesName());
            this.equipeBean.setCanCreateMatch(this.equipeBean.canCreateMatch());
        }
        else
        {
            for(int i = 0; i < equipes.size(); i++)
            {
                if(this.equipeBean.getNomEquipe().equals(equipes.get(i).getNomEquipe()))
                {
                    this.isPresent = true;
                    break;
                }
                else this.isPresent = false;
            }
            if(!this.isPresent)
            {
                EquipeEntity message = new EquipeEntity();
                message.setNomEquipe(this.equipeBean.getNomEquipe());
                em.getTransaction().begin();
                em.persist(message);
                em.getTransaction().commit();
                this.equipeBean.setEquipesName(this.equipeBean.getAllEquipesName());
                this.equipeBean.setCanCreateMatch(this.equipeBean.canCreateMatch());
            }
        }
    }

    public List<EquipeEntity> getAllEquipes() {

        Query query = em.createNamedQuery("findAllEquipes",
                EquipeEntity.class);
        List<EquipeEntity> equipes = query.getResultList();
        return equipes;
    }

    public List<String> getAllEquipesName()
    {
        return this.equipeBean.getAllEquipesName();
    }

    public void setNomEquipe(String nomEquipe)
    {
        this.equipeBean.setNomEquipe(nomEquipe);
    }
    public String getNomEquipe()
    {
        return this.equipeBean.getNomEquipe();
    }

    public List<String> getEquipesName() {
        return this.equipeBean.getEquipesName();
    }

    public void setEquipesName(List<String> equipesName) {
        this.equipeBean.setEquipesName(equipesName);
    }

    public boolean isCanCreateMatch()
    {
        return this.equipeBean.isCanCreateMatch();
    }

    public void setCanCreateMatch(boolean canCreateMatch)
    {
        this.equipeBean.setCanCreateMatch(canCreateMatch);
    }

    public String getEquipeNameVS1() {
        return this.equipeBean.getEquipeNameVS1();
    }

    public void setEquipeNameVS1(String equipeNameVS1) {
        this.equipeBean.setEquipeNameVS1(equipeNameVS1);
    }

    public String getEquipeNameVS2() {
        return this.equipeBean.getEquipeNameVS2();
    }

    public void setEquipeNameVS2(String equipeNameVS2) {
        this.equipeBean.setEquipeNameVS2(equipeNameVS2);
    }
}
