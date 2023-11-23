package com.mesdevis.entity;

import jakarta.persistence.*;

@Entity(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_client", nullable=false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable=false)
    private Utilisateur utilisateur;

    public Client(long id, Utilisateur utilisateur) {
        this.id = id;
        this.utilisateur = utilisateur;
    }

    public Client(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Client() {

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
