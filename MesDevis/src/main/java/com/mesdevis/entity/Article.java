package com.mesdevis.entity;

import jakarta.persistence.*;

@Entity(name="article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_article", nullable=false)
    private long id;

    @Column(name="libelle_article", nullable = false)
    private String libelle;

    @Column(name="prix_ht_article", nullable = false)
    private float prixHT;

    @ManyToOne
    @JoinColumn(name = "id_categorie_article", nullable=false)
    private CategorieArticle categorie;

    /**
     * Constructeur
     * @param id
     * @param libelle
     * @param prixHT
     * @param categorie
     */
    public Article(long id, String libelle, float prixHT, CategorieArticle categorie) {
        this.id = id;
        this.libelle = libelle;
        this.prixHT = prixHT;
        this.categorie = categorie;
    }

    /**
     * Constructeur sans id
     * @param libelle
     * @param prixHT
     * @param categorie
     */
    public Article(String libelle, float prixHT, CategorieArticle categorie) {
        this.libelle = libelle;
        this.prixHT = prixHT;
        this.categorie = categorie;
    }

    public Article() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(float prixHT) {
        this.prixHT = prixHT;
    }

    public CategorieArticle getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieArticle categorie) {
        this.categorie = categorie;
    }
}