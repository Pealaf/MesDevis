package com.mesdevis.service;

import com.mesdevis.entity.dto.CategorieArticleDTO;

import java.util.List;
import java.util.Optional;

public interface CategorieArticleService {

    /**
     * Lit toutes les CategorieArticle depuis la BDD
     * @return liste des CategorieArticle lues transformées en DTO
     */
    List<Optional<CategorieArticleDTO>> findAll();

    /**
     * Lit une CategorieArticle en BDD à partir de son identifiant
     * @param id : identifiant de l'CategorieArticle à lire
     * @return la catégorie de l'article lue et transformée en DTO
     */
    Optional<CategorieArticleDTO> findById(long id);

    /**
     * Ajoute une CategorieArticle en BDD
     * @param categorieArticleDTO CategorieArticle à ajouter au format DTO
     * @return CategorieArticle ajoutée avec son identifiant auto-genéré par la BDD au format DTO
     */
    Optional<CategorieArticleDTO> ajouter(CategorieArticleDTO categorieArticleDTO);

    /**
     * Modifie une catégorie d'article en BDD si elle existe (id renseigné) ou ajoute la catégorie de l'article en BDD
     * @param categorieArticleDTO catégorie d'article à modifier au format DTO
     * @return CategorieArticle modifée avec son identifiant auto-genéré par la BDD en cas de création au format DTO
     */
    Optional<CategorieArticleDTO> modifier(CategorieArticleDTO categorieArticleDTO);

    /**
     * Supprime une CategorieArticle en BDD
     * @param categorieArticleDTO CategorieArticle à supprimer au format DTO
     */
    void supprimer(CategorieArticleDTO categorieArticleDTO);
}
