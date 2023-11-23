package com.mesdevis.entity;

import jakarta.persistence.*;

@Entity(name="panier")
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_panier", nullable=false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable=false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_article", nullable=false)
    private Article article;

    @Column(name="quantite_articles", nullable = false)
    private int quantiteArticles;

    public Panier(long id, Utilisateur utilisateur, Article article, int quantiteArticles) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.article = article;
        this.quantiteArticles = quantiteArticles;
    }

    public Panier(Utilisateur utilisateur, Article article, int quantiteArticles) {
        this.utilisateur = utilisateur;
        this.article = article;
        this.quantiteArticles = quantiteArticles;
    }

    public Panier() {

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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantiteArticles() {
        return quantiteArticles;
    }

    public void setQuantiteArticles(int quantiteArticles) {
        this.quantiteArticles = quantiteArticles;
    }
}