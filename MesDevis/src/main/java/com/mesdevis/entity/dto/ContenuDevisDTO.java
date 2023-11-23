package com.mesdevis.entity.dto;

import com.mesdevis.entity.Article;
import com.mesdevis.entity.Devis;

import java.io.Serializable;
import java.util.Objects;

public class ContenuDevisDTO implements Serializable {

    private long id;
    private Devis devis;
    private Article article;
    private int quantiteArticles;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContenuDevisDTO that = (ContenuDevisDTO) o;
        return id == that.getId() && Objects.equals(devis, that.getDevis()) && Objects.equals(article, that.getArticle()) && quantiteArticles == that.getQuantiteArticles();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, devis, article, quantiteArticles);
    }

    @Override
    public String toString() {
        return "ContenuDevisDTO{" +
                "id = " + id +
                ", devis = '" + devis +
                ", article = '" + article +
                ", quantiteArticles = '" + quantiteArticles +
                '}';
    }
}