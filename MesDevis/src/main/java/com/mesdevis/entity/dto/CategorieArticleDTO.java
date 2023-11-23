package com.mesdevis.entity.dto;

import com.mesdevis.entity.Commercial;

import java.io.Serializable;
import java.util.Objects;

public class CategorieArticleDTO implements Serializable {

    private long id;
    private String intitule;

    private Commercial commercial;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorieArticleDTO that = (CategorieArticleDTO) o;
        return id == that.getId() && Objects.equals(intitule, that.getIntitule()) && Objects.equals(commercial, that.getCommercial());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, intitule, commercial);
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id = " + id +
                ", intitulé = '" + intitule +
                ", commercial = '" + commercial +
                '}';
    }
}