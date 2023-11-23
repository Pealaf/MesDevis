package com.mesdevis.entity.dto;

import com.mesdevis.entity.Utilisateur;

import java.io.Serializable;
import java.util.Objects;

public class CommercialDTO implements Serializable {

    private long id;

    private Utilisateur utilisateur;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommercialDTO that = (CommercialDTO) o;
        return id == that.getId() && Objects.equals(utilisateur, that.getUtilisateur());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, utilisateur);
    }

    @Override
    public String toString() {
        return "CommercialDTO{" +
                "id = " + id +
                ", utilisateur = " + utilisateur +
                '}';
    }
}