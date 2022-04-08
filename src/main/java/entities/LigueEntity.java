package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = "findAllLigues", query = "SELECT l FROM LigueEntity l")
})

@Entity
public class LigueEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String nomLigue;

    public String getNomLigue() {
        return nomLigue;
    }

    public void setNomLigue(String nomLigue) {
        this.nomLigue = nomLigue;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
