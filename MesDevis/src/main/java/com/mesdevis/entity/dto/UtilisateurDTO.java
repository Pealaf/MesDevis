package com.mesdevis.entity.dto;

import java.io.Serializable;
import java.util.Objects;

public class UtilisateurDTO implements Serializable {

    private long id;
    private String nom;
    private String prenom;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilisateurDTO that = (UtilisateurDTO) o;
        return id == that.getId() && Objects.equals(nom, that.getNom()) && Objects.equals(prenom, that.getPrenom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom);
    }

    @Override
    public String toString() {
        return "UtilisateurDTO{" +
                "id = " + id +
                ", nom = '" + nom +
                ", prénom = '" + prenom +
                '}';
    }
}