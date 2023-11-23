package com.mesdevis.entity;

import jakarta.persistence.*;

@Entity(name="categorie_article")
public class CategorieArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_categorie_article", nullable=false)
    private long id;

    @Column(name="intitule_categorie", nullable = false)
    private String intitule;

    @ManyToOne
    @JoinColumn(name = "id_commercial", nullable=false)
    private Commercial commercial;

    /**
     * Constructeur
     * @param id
     * @param intitule
     * @param commercial
     */
    public CategorieArticle(long id, String intitule, Commercial commercial) {
        this.id = id;
        this.intitule = intitule;
        this.commercial = commercial;
    }

    /**
     * Constructeur sans id
     * @param intitule
     */
    public CategorieArticle(String intitule, Commercial commercial) {
        this.intitule = intitule;
        this.commercial = commercial;
    }

    public CategorieArticle() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Commercial getCommercial() {
        return commercial;
    }

    public void setCommercial(Commercial commercial) {
        this.commercial = commercial;
    }
}