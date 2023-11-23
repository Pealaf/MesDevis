package com.mesdevis.service;

import com.mesdevis.entity.dto.DevisDTO;
import com.mesdevis.entity.dto.UtilisateurDTO;

import java.util.List;
import java.util.Optional;

public interface DevisService {

    /**
     * Lit tous les devis depuis la BDD
     * @return liste des devis lus transformés en DTO
     */
    List<Optional<DevisDTO>> findAll();

    /**
     * Lit un devis en BDD à partir de son identifiant
     * @param id : identifiant du devis à lire
     * @return le devis lu et transformé en DTO
     */
    Optional<DevisDTO> findById(long id);

    /**
     * Ajoute un devis en BDD
     * @param devisDTO Devis à ajouter au format DTO
     * @return Devis ajouté avec son identifiant auto-genéré par la BDD au format DTO
     */
    Optional<DevisDTO> ajouter(DevisDTO devisDTO);

    /**
     * Modifie un devis en BDD s'il existe (id renseigné) ou ajoute le devis en BDD
     * @param devisDTO Devis à modifier au format DTO
     * @return Devis modifé avec son identifiant auto-genéré par la BDD en cas de création au format DTO
     */
    Optional<DevisDTO> modifier(DevisDTO devisDTO);

    /**
     * Supprime un devis en BDD
     * @param devisDTO Devis à supprimer au format DTO
     */
    void supprimer(DevisDTO devisDTO);

    /**
     * Ajoute un devis en BDD
     * @param utilisateurDTO utilisateur à ajouter au format DTO
     * @return Devis ajouté avec son identifiant auto-genéré par la BDD au format DTO
     */
    Optional<DevisDTO> genererDevis(UtilisateurDTO utilisateurDTO);
}
