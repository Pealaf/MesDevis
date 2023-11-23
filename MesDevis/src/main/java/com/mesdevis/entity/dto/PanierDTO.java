package com.mesdevis.entity.dto;

import com.mesdevis.entity.Article;
import com.mesdevis.entity.Utilisateur;

import java.io.Serializable;
import java.util.Objects;

public class PanierDTO implements Serializable {

    private long id;
    private Utilisateur utilisateur;
    private Article article;
    private int quantiteArticles;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PanierDTO that = (PanierDTO) o;
        return id == that.getId() && Objects.equals(utilisateur, that.getUtilisateur()) && Objects.equals(article, that.getArticle()) && quantiteArticles == that.getQuantiteArticles();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, utilisateur, article, quantiteArticles);
    }

    @Override
    public String toString() {
        return "PanierDTO{" +
                "id = " + id +
                ", utilisateur = '" + utilisateur +
                ", article = '" + article +
                ", quantiteArticles = '" + quantiteArticles +
                '}';
    }
}