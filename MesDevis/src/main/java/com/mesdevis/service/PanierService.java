package com.mesdevis.service;

import com.mesdevis.entity.dto.PanierDTO;
import com.mesdevis.entity.dto.UtilisateurDTO;

import java.util.List;
import java.util.Optional;

public interface PanierService {

    /**
     * Lit tous les paniers depuis la BDD
     * @return liste des paniers lus transformés en DTO
     */
    List<Optional<PanierDTO>> findAll();

    /**
     * Lit un panier en BDD à partir de son identifiant
     * @param id : identifiant du panier à lire
     * @return le panier lu et transformé en DTO
     */
    Optional<PanierDTO> findById(long id);

    /**
     * Ajoute un panier en BDD
     * @param panierDTO Panier à ajouter au format DTO
     * @return Panier ajouté avec son identifiant auto-genéré par la BDD au format DTO
     */
    Optional<PanierDTO> ajouter(PanierDTO panierDTO);

    /**
     * Modifie un panier en BDD s'il existe (id renseigné) ou ajoute le panier en BDD
     * @param panierDTO Panier à modifier au format DTO
     * @return Panier modifé avec son identifiant auto-genéré par la BDD en cas de création au format DTO
     */
    Optional<PanierDTO> modifier(PanierDTO panierDTO);

    /**
     * Supprime un panier en BDD
     * @param panierDTO Panier à supprimer au format DTO
     */
    void supprimer(PanierDTO panierDTO);

    /**
     * Lit tous les paniers d'un utilisateur depuis la BDD
     * @param utilisateurDTO : utilisateur
     * @return liste des paniers de l'utilisateur lus transformés en DTO
     */
    List<Optional<PanierDTO>> findAllByUtilisateur(UtilisateurDTO utilisateurDTO);
}
