package com.mesdevis.service;

import com.mesdevis.entity.dto.ArticleDTO;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    /**
     * Lit tous les articles depuis la BDD
     * @return liste des articles lus transformés en DTO
     */
    List<Optional<ArticleDTO>> findAll();

    /**
     * Lit un article en BDD à partir de son identifiant
     * @param id : identifiant de l'article à lire
     * @return l'article lu et transformé en DTO
     */
    Optional<ArticleDTO> findById(long id);

    /**
     * Ajoute un article en BDD
     * @param ArticleDTO Article à ajouter au format DTO
     * @return Article ajouté avec son identifiant auto-genéré par la BDD au format DTO
     */
    Optional<ArticleDTO> ajouter(ArticleDTO ArticleDTO);

    /**
     * Modifie un article en BDD s'il existe (id renseigné) ou ajoute l'article en BDD
     * @param ArticleDTO Article à modifier au format DTO
     * @return Article modifé avec son identifiant auto-genéré par la BDD en cas de création au format DTO
     */
    Optional<ArticleDTO> modifier(ArticleDTO ArticleDTO);

    /**
     * Supprime un article en BDD
     * @param ArticleDTO Article à supprimer au format DTO
     */
    void supprimer(ArticleDTO ArticleDTO);
}
