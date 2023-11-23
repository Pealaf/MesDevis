package com.mesdevis.entity.dto;

import com.mesdevis.entity.CategorieArticle;

import java.io.Serializable;
import java.util.Objects;

public class ArticleDTO implements Serializable{

    private long id;
    private String libelle;
    private CategorieArticle categorie;
    private float prixHT;

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

    public CategorieArticle getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieArticle categorie) {
        this.categorie = categorie;
    }

    public float getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(float prixHT) {
        this.prixHT = prixHT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDTO that = (ArticleDTO) o;
        return id == that.getId() && Objects.equals(libelle, that.getLibelle()) && categorie == that.getCategorie() && Float.compare(prixHT, that.getPrixHT()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libelle, categorie, prixHT);
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id = " + id +
                ", libelle = '" + libelle +
                ", categorie = " + categorie +
                ", prixHT = " + prixHT +
                '}';
    }
}