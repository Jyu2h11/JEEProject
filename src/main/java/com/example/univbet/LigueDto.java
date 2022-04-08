package com.example.univbet;

import com.sun.xml.internal.ws.developer.Serialization;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("ligue")
@SessionScoped
public class LigueDto implements Serializable {
    private String nomLigue;

    public String getNomLigue() {
        return nomLigue;
    }

    public void setNomLigue(String nomLigue) {
        this.nomLigue = nomLigue;
    }
}
