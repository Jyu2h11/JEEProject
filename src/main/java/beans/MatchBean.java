package beans;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MatchBean {

    private List<String> resultMatch;

    MatchBean()
    {
        resultMatch = new ArrayList<>();
    }

    public List<String> getResultMatch() {
        return resultMatch;
    }

    public void setResultMatch(List<String> resultMatch) {
        this.resultMatch = resultMatch;
    }
}
