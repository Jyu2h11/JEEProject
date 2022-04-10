package beans;

import entities.EquipeEntity;
import entities.MatchEntity;
import listeners.ApplicationListener;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class EquipeBean {

    private String nomEquipe;
    private String equipeNameVS1;
    private String equipeNameVS2;
    List<String> equipesName;
    boolean canCreateMatch;
    EntityManagerFactory emf;
    EntityManager em;

    EquipeBean()
    {
        initializeBDD();
        equipesName = getAllEquipesName();
        canCreateMatch = canCreateMatch();
    }

    public void initializeBDD()
    {
        emf = ApplicationListener.getEmf();
        em = emf.createEntityManager();
    }

    public List<String> getAllEquipesName()
    {
        Query query = em.createQuery("SELECT e.nomEquipe FROM EquipeEntity e", String.class);
        List<String> equipesName = query.getResultList();
        return equipesName;
    }

    public List<Long> getEquipeIdByName(String chosenTeam)
    {
        Query query = em.createQuery("SELECT e.id FROM EquipeEntity e WHERE e.nomEquipe = '" +
                chosenTeam + "'", Long.class);
        return query.getResultList();
    }

    public List<EquipeEntity> getAllEquipesById(String chosenTeam)
    {
        Query query = em.createQuery("SELECT e from EquipeEntity e WHERE e.id = " + getEquipeIdByName(chosenTeam).get(0),
                EquipeEntity.class);
        return query.getResultList();
    }

    public boolean canCreateMatch()
    {
        return getAllEquipesName().size() >= 2;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public List<String> getEquipesName() {
        return equipesName;
    }

    public void setEquipesName(List<String> equipesName) {
        this.equipesName = equipesName;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public boolean isCanCreateMatch() {
        return canCreateMatch;
    }

    public String getEquipeNameVS1() {
        return equipeNameVS1;
    }

    public void setEquipeNameVS1(String equipeNameVS1) {
        this.equipeNameVS1 = equipeNameVS1;
    }

    public String getEquipeNameVS2() {
        return equipeNameVS2;
    }

    public void setEquipeNameVS2(String equipeNameVS2) {
        this.equipeNameVS2 = equipeNameVS2;
    }

    public void setCanCreateMatch(boolean canCreateMatch) {
        this.canCreateMatch = canCreateMatch;
    }
}
