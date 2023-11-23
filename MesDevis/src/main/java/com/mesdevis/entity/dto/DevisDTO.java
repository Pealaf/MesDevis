package com.mesdevis.entity.dto;

import com.mesdevis.entity.Utilisateur;

import java.io.Serializable;
import java.util.Objects;

public class DevisDTO implements Serializable {

    private long id;
    private boolean validation;
    private boolean paiement;
    private Utilisateur utilisateur;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public boolean isPaiement() {
        return paiement;
    }

    public void setPaiement(boolean paiement) {
        this.paiement = paiement;
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
        DevisDTO that = (DevisDTO) o;
        return id == that.getId() && validation == that.isValidation() && paiement == that.isPaiement() && Objects.equals(utilisateur, that.getUtilisateur());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, validation, paiement, utilisateur);
    }

    @Override
    public String toString() {
        return "DevisDTO{" +
                "id = " + id +
                ", validation = '" + validation +
                ", paiement = '" + paiement +
                ", utilisateur = '" + utilisateur +
                '}';
    }
}
