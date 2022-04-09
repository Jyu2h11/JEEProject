package beans;

import com.example.univbet.LigueAndUserDto;
import entities.LigueEntity;
import entities.UserEntity;
import listeners.ApplicationListener;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class LigueBean {

    private String nomLigue;
    private String nomLigue2;



    EntityManagerFactory emf;
    EntityManager em;
    List<LigueEntity> ligues;
    @EJB
    UserBean currentUser;

    LigueBean()
    {
        initializeBDD();
    }

    public List<LigueEntity> getLigues() {
        return ligues;
    }

    public void setLigues(List<LigueEntity> ligues) {
        this.ligues = ligues;
    }

    private boolean userPresentInLigue;


    public void initializeBDD()
    {
        emf = ApplicationListener.getEmf();
        em = emf.createEntityManager();
    }

    public void setUserPresentInLigue() {
        initializeBDD();
        ligues = getAllLigues();
        setUserPresentInLigue(verifyUserHasLiguePresent());
    }
    public boolean verifyUserHasLiguePresent()
    {
        System.out.println(getAllUserHasLigues());
        for(int i = 0; i < getAllUserHasLigues().size(); i++)
        {
            if(this.currentUser.getLogin().equals(getAllUserHasLigues().get(i).getLogin()))
            {
                setUserPresentInLigue(true);
                System.out.println(getLigueNameOfUserList());
                break;
            }
            else setUserPresentInLigue(false);
        }
        return isUserPresentInLigue();
    }
    public List<Long> getCurrentUserId()
    {
        Query query = em.createQuery("SELECT u.id FROM UserEntity  u WHERE u.login = '" + this.currentUser.getLogin() + "'",
                Long.class);
        List<Long> userId = query.getResultList();
        return userId;
    }

    public List<UserEntity> getAllUserHasLigues()
    {

        Query query = em.createQuery("SELECT ul.userEntity FROM UserHasLigueEntity ul WHERE ul.userEntity = '" + getCurrentUserId().get(0) + "'",
                UserEntity.class);
        List<UserEntity> userHasLigueId = query.getResultList();
        return userHasLigueId;
    }

    public List<LigueEntity> getAllLigues()
    {
        Query query = em.createNamedQuery("findAllLigues",
                LigueEntity.class);
        List<LigueEntity> ligues = query.getResultList();
        return ligues;
    }

    public List<String> getAllLiguesName()
    {
        Query query = em.createQuery("SELECT l.nomLigue FROM LigueEntity l", String.class);
        List<String> liguesName = query.getResultList();
        return liguesName;
    }

    public List<String> getLigueNameOfUserList()
    {
        List<Long> userIdList = getCurrentUserId();
        Long userId = userIdList.get(0);
        Query query = em.createQuery("SELECT l.nomLigue FROM LigueEntity l, UserHasLigueEntity ul, UserEntity u WHERE u.id = " + userId + " and l.id = ul.ligueEntity.id and ul.userEntity.id = u.id",
                String.class);
        List<String> ligueNameOfUser = query.getResultList();
        return ligueNameOfUser;
    }



    public String getNomLigue() {
        return nomLigue;
    }

    public void setNomLigue(String nomLigue) {
        this.nomLigue = nomLigue;
    }

    public String getNomLigue2() {
        return nomLigue2;
    }

    public void setNomLigue2(String nomLigue2) {
        this.nomLigue2 = nomLigue2;
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

    public boolean isUserPresentInLigue() {
        return userPresentInLigue;
    }

    public void setUserPresentInLigue(boolean userPresentInLigue) {
        this.userPresentInLigue = userPresentInLigue;
    }
}
