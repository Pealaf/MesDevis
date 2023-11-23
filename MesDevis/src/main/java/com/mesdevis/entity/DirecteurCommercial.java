package com.mesdevis.entity;

import jakarta.persistence.*;

@Entity(name="directeur_commercial")
public class DirecteurCommercial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_directeur_commercial", nullable=false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable=false)
    private Utilisateur utilisateur;

    public DirecteurCommercial(long id, Utilisateur utilisateur) {
        this.id = id;
        this.utilisateur = utilisateur;
    }

    public DirecteurCommercial(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public DirecteurCommercial() {

    }

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
}
