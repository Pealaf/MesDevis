package com.mesdevis.service;

import com.mesdevis.entity.dto.UtilisateurDTO;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    /**
     * Lit tous les utilisateurs depuis la BDD
     * @return liste des utilisateurs lus transformés en DTO
     */
    List<Optional<UtilisateurDTO>> findAll();

    /**
     * Lit un utilisateur en BDD à partir de son identifiant
     * @param id : identifiant de l'utilisateur à lire
     * @return l'utilisateur lu et transformé en DTO
     */
    Optional<UtilisateurDTO> findById(long id);

    /**
     * Ajoute un utilisateur en BDD
     * @param utilisateurDTO Utilisateur à ajouter au format DTO
     * @return Utilisateur ajouté avec son identifiant auto-genéré par la BDD au format DTO
     */
    Optional<UtilisateurDTO> ajouter(UtilisateurDTO utilisateurDTO);

    /**
     * Modifie un utilisateur en BDD s'il existe (id renseigné) ou ajoute l'utilisateur en BDD
     * @param utilisateurDTO utilisateur à modifier au format DTO
     * @return Utilisateur modifé avec son identifiant auto-genéré par la BDD en cas de création au format DTO
     */
    Optional<UtilisateurDTO> modifier(UtilisateurDTO utilisateurDTO);

    /**
     * Supprime un Utilisateur en BDD
     * @param utilisateurDTO Utilisateur à supprimer au format DTO
     */
    void supprimer(UtilisateurDTO utilisateurDTO);

    /**
     * Méthode permettant de valiser un panier.
     * C'est-à-dire qu'elle crée un devis, le remplit puis supprime le contenu du panier.
     * @param utilisateurDTO Utilisateur au format DTO
     * @return true si la validation s'est effectuée avec succès et false sinon
     */
    //public boolean validerPanier(UtilisateurDTO utilisateurDTO);
}
