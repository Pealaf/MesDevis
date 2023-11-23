package com.mesdevis.entity;

import jakarta.persistence.*;

@Entity(name="devis")
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_devis", nullable=false)
    private long id;

    @Column(name="validation_devis", nullable = false)
    private boolean validation;

    @Column(name="paiement_devis", nullable = false)
    private boolean paiement;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable=false)
    private Utilisateur utilisateur;

    /**
     * Constructeur
     * @param id
     * @param validation
     * @param paiement
     * @param utilisateur
     */
    public Devis(long id, boolean validation, boolean paiement, Utilisateur utilisateur) {
        this.id = id;
        this.validation = validation;
        this.paiement = paiement;
        this.utilisateur = utilisateur;
    }

    /**
     * Constructeur sans id
     * @param validation
     * @param paiement
     * @param utilisateur
     */
    public Devis(boolean validation, boolean paiement, Utilisateur utilisateur) {
        this.validation = validation;
        this.paiement = paiement;
        this.utilisateur = utilisateur;
    }

    public Devis() {

    }

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
}