package com.mesdevis.entity;

import jakarta.persistence.*;

@Entity(name="contenu_devis")
public class ContenuDevis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_contenu_devis", nullable=false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_devis", nullable=false)
    private Devis devis;

    @ManyToOne
    @JoinColumn(name = "id_article", nullable=false)
    private Article article;

    @Column(name="quantite_articles", nullable = false)
    private int quantiteArticles;

    public ContenuDevis(long id, Devis devis, Article article, int quantiteArticles) {
        this.id = id;
        this.devis = devis;
        this.article = article;
        this.quantiteArticles = quantiteArticles;
    }

    public ContenuDevis(Devis devis, Article article, int quantiteArticles) {
        this.devis = devis;
        this.article = article;
        this.quantiteArticles = quantiteArticles;
    }

    public ContenuDevis() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
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
