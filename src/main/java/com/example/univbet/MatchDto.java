package com.example.univbet;

import beans.EquipeBean;
import beans.MatchBean;
import entities.MatchEntity;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("match")
@SessionScoped
public class MatchDto implements Serializable {
    private boolean isPresent;
    EntityManagerFactory emf;
    EntityManager em;
    List<MatchEntity> matchs;
    List<MatchEntity> lol;
    private String toAdd;
    private boolean isOver;

    @EJB
    EquipeBean equipeBean;
    @EJB
    MatchBean matchBean;

    public void addMatch()
    {
        this.emf = this.equipeBean.getEmf();
        this.em = this.equipeBean.getEm();
        matchs = getAllMatchs();
        if(matchs.isEmpty())
        {
            if(!(this.equipeBean.getEquipeNameVS1() == this.equipeBean.getEquipeNameVS2())) {
                MatchEntity message = new MatchEntity();
                message.setEquipeEntity(this.equipeBean.getAllEquipesById(this.equipeBean.getEquipeNameVS1()).get(0));
                message.setEquipeEntity2(this.equipeBean.getAllEquipesById(this.equipeBean.getEquipeNameVS2()).get(0));
                message.setOver(false);
                message.setMatchResult("");
                em.getTransaction().begin();
                em.persist(message);
                em.getTransaction().commit();
            }
        }
        else
        {
            for(int i = 0; i < matchs.size(); i++)
            {
                if(getAllMatchs().get(i).getEquipeEntity().getNomEquipe().equals(this.equipeBean.getEquipeNameVS1()) &&
                getAllMatchs().get(i).getEquipeEntity2().getNomEquipe().equals(this.equipeBean.getEquipeNameVS2()))
                {
                    this.isPresent = true;
                    break;
                }
                else this.isPresent = false;
            }
            if(!this.isPresent)
            {
                if(this.equipeBean.getEquipeNameVS1() != this.equipeBean.getEquipeNameVS2()) {
                    MatchEntity message = new MatchEntity();
                    message.setEquipeEntity(this.equipeBean.getAllEquipesById(this.equipeBean.getEquipeNameVS1()).get(0));
                    message.setEquipeEntity2(this.equipeBean.getAllEquipesById(this.equipeBean.getEquipeNameVS2()).get(0));
                    message.setOver(false);
                    message.setMatchResult("");
                    em.getTransaction().begin();
                    em.persist(message);
                    em.getTransaction().commit();
                }
            }
        }
    }

    public List<MatchEntity> getAllMatchs()
    {
        Query query = em.createNamedQuery("findAllMatchs", MatchEntity.class);
        return query.getResultList();
    }

    public List<MatchEntity> getAllMatchsOver()
    {
        this.emf = this.equipeBean.getEmf();
        this.em = this.equipeBean.getEm();
        Query query = em.createQuery("SELECT m FROM MatchEntity m WHERE m.isOver = true", MatchEntity.class);
        return query.getResultList();
    }
    public List<MatchEntity> getAll3MatchsOver()
    {
        this.emf = this.equipeBean.getEmf();
        this.em = this.equipeBean.getEm();
        Query query = em.createQuery("SELECT m FROM MatchEntity m WHERE m.isOver = true", MatchEntity.class).setMaxResults(3);
        return query.getResultList();
    }

    public List<MatchEntity> getAllMatchsToGo()
    {
        this.emf = this.equipeBean.getEmf();
        this.em = this.equipeBean.getEm();
        Query query = em.createQuery("SELECT m FROM MatchEntity m WHERE m.isOver = false", MatchEntity.class);
        return query.getResultList();
    }

    public List<MatchEntity> getAll3MatchsToGo()
    {
        this.emf = this.equipeBean.getEmf();
        this.em = this.equipeBean.getEm();
        Query query = em.createQuery("SELECT m FROM MatchEntity m WHERE m.isOver = false", MatchEntity.class).setMaxResults(3);
        return query.getResultList();
    }
    public String getLastMatchOver()
    {
        if(!getAllMatchsOver().isEmpty()) {
            String displayLastMatchover = getAllMatchsOver().get(getAllMatchsOver().size() - 1).getEquipeEntity().getNomEquipe()
                    + " VS " + getAllMatchsOver().get(getAllMatchsOver().size() - 1).getEquipeEntity2().getNomEquipe() + " Résultat : " + getAllMatchsOver().get(getAllMatchsOver().size() - 1).getMatchResult();
            return displayLastMatchover;
        }
        return null;
    }

    public List<String> get3LastMatchOver()
    {
        List<String> display3LastMatchOver = new ArrayList<>();
        if(!getAll3MatchsOver().isEmpty())
        {
            for(int i = 0; i < getAll3MatchsOver().size(); i++) {
                display3LastMatchOver.add(getAll3MatchsOver().get(i).getEquipeEntity().getNomEquipe() + " VS " +
                        getAll3MatchsOver().get(i).getEquipeEntity2().getNomEquipe() + " Résultat : " +
                        getAll3MatchsOver().get(i).getMatchResult());
            }
        }
        return display3LastMatchOver;
    }
    public List<String> get3LastMatchToGo()
    {
        List<String> display3LastMatchToGo = new ArrayList<>();
        if(!getAll3MatchsToGo().isEmpty())
        {
            for(int i = 0; i < getAll3MatchsToGo().size(); i++) {
                display3LastMatchToGo.add(getAll3MatchsToGo().get(i).getEquipeEntity().getNomEquipe() + " VS " +
                        getAll3MatchsToGo().get(i).getEquipeEntity2().getNomEquipe());
            }
        }
        return display3LastMatchToGo;
    }

    public String getNextMatch()
    {
        this.emf = this.equipeBean.getEmf();
        this.em = this.equipeBean.getEm();
        if(!getAllMatchsToGo().isEmpty()) {
            String displayLastMatchover = getAllMatchsToGo().get(getAllMatchsToGo().size() - 1).getEquipeEntity().getNomEquipe()
                    + " VS " + getAllMatchsToGo().get(getAllMatchsToGo().size() - 1).getEquipeEntity2().getNomEquipe();
            return displayLastMatchover;
        }
        return null;
    }
    
    public List<String> displayAllMatchs()
    {
        this.emf = this.equipeBean.getEmf();
        this.em = this.equipeBean.getEm();
        matchs = getAllMatchs();
        List<String> displayMatchs = new ArrayList<>();
        for(int i = 0; i < matchs.size(); i++)
        {
            displayMatchs.add(matchs.get(i).getEquipeEntity().getNomEquipe() + " VS " + matchs.get(i).getEquipeEntity2().getNomEquipe());
        }
        return displayMatchs;
    }

    public void addInResult(String element)
    {
        this.matchBean.getResultMatch().add(element);
        System.out.println(getResultMatch());
    }

    public void validateMatch(String match)
    {

        this.emf = this.equipeBean.getEmf();
        this.em = this.equipeBean.getEm();
        String equipe1;
        String equipe2;
        for(int i = 0; i < matchs.size(); i++)
        {
            if(match.contains(matchs.get(i).getEquipeEntity().getNomEquipe()) && match.contains(matchs.get(i).getEquipeEntity2().getNomEquipe()))
            {
              String[] split = match.split(" VS ");
              equipe1 = split[0];
              equipe2 = split[1];
              lol = getMatchFromEquipe(equipe1, equipe2);
              updateMatchStatus(getMatchId(), getResultMatch().get(0));
            }
        }
    }

    public List<MatchEntity> getMatchFromEquipe(String equipe1, String equipe2)
    {
        this.emf = this.equipeBean.getEmf();
        this.em = this.equipeBean.getEm();
        Query query = em.createQuery("SELECT m FROM MatchEntity m, EquipeEntity e, EquipeEntity e2 WHERE m.equipeEntity2 = " + getEquipeIdByName(equipe2).get(0) + " AND m.equipeEntity = " +
                getEquipeIdByName(equipe1).get(0) + " AND e.id = " + getEquipeIdByName(equipe2).get(0) + " AND e2.id = " + getEquipeIdByName(equipe1).get(0), MatchEntity.class);
        return query.getResultList();
    }

    public Long getMatchId()
    {
        return lol.get(0).getId();
    }

    @Transactional
    public void updateMatchStatus(Long id, String resultMatch)
    {
        this.emf = this.equipeBean.getEmf();
        this.em = this.equipeBean.getEm();
        em.getTransaction().begin();
        em.createQuery("UPDATE MatchEntity m SET m.isOver = true, m.matchResult = '" + resultMatch + "' WHERE m.id = " + id).executeUpdate();
        em.getTransaction().commit();
    }

    public List<Boolean> getIsOverMatch(Long id)
    {
        this.emf = this.equipeBean.getEmf();
        this.em = this.equipeBean.getEm();
        return em.createQuery("SELECT m.isOver FROM MatchEntity m WHERE m.id = " + id, Boolean.class).getResultList();
    }



    public List<Long> getEquipeIdByName(String chosenTeam)
    {
        this.emf = this.equipeBean.getEmf();
        this.em = this.equipeBean.getEm();
        Query query = em.createQuery("SELECT e.id FROM EquipeEntity e WHERE e.nomEquipe = '" +
                chosenTeam + "'", Long.class);
        return query.getResultList();
    }

    public List<String> getResultMatch() {
        return this.matchBean.getResultMatch();
    }



    public String getToAdd() {
        return toAdd;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public void setToAdd(String toAdd) {
        this.toAdd = toAdd;
    }

    public void setResultMatch(List<String> resultMatch) {
        this.matchBean.setResultMatch(resultMatch);
    }
}
