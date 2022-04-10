package entities;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = "findAllMatchs", query = "SELECT m FROM MatchEntity m")
})
@Entity
public class MatchEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private EquipeEntity equipeEntity;


    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private EquipeEntity equipeEntity2;

    @NotNull
    private boolean isOver;

    @NotNull
    private String matchResult;

    public EquipeEntity getEquipeEntity() {
        return equipeEntity;
    }

    public void setEquipeEntity(EquipeEntity equipeEntity) {
        this.equipeEntity = equipeEntity;
    }

    public EquipeEntity getEquipeEntity2() {
        return equipeEntity2;
    }

    public void setEquipeEntity2(EquipeEntity equipeEntity2) {
        this.equipeEntity2 = equipeEntity2;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public String getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }
}
