package entities;

import javax.persistence.*;

@Entity
public class UserHasLigueEntity {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private UserEntity userEntity;


    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private LigueEntity ligueEntity;


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public LigueEntity getLigueEntity() {
        return ligueEntity;
    }

    public void setLigueEntity(LigueEntity ligueEntity) {
        this.ligueEntity = ligueEntity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
