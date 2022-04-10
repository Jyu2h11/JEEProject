package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "findAllEquipes", query = "SELECT e FROM EquipeEntity e")
})
@Entity
public class EquipeEntity {
    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    private String nomEquipe;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }
}
