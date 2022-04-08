package beans;

import com.example.univbet.LigueAndUserDto;

import javax.ejb.Stateless;

@Stateless
public class LigueBean {

    private String nomLigue;

    private boolean userPresentInLigue;



    public String getNomLigue() {
        return nomLigue;
    }

    public void setNomLigue(String nomLigue) {
        this.nomLigue = nomLigue;
    }


    public boolean isUserPresentInLigue() {
        return userPresentInLigue;
    }

    public void setUserPresentInLigue(boolean userPresentInLigue) {
        this.userPresentInLigue = userPresentInLigue;
    }
}
