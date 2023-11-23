package com.mesdevis.entity;

import jakarta.persistence.*;

@Entity(name="commercial")
public class Commercial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_commercial", nullable=false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable=false)
    private Utilisateur utilisateur;

    public Commercial(long id, Utilisateur utilisateur) {
        this.id = id;
        this.utilisateur = utilisateur;
    }

    public Commercial(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Commercial() {

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
