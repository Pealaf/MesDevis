package com.mesdevis.service;

import com.mesdevis.entity.dto.ContenuDevisDTO;
import com.mesdevis.entity.dto.DevisDTO;
import com.mesdevis.entity.dto.PanierDTO;

import java.util.List;
import java.util.Optional;

public interface ContenuDevisService {

    /**
     * Lit tous les contenus des devis depuis la BDD
     *
     * @return liste des contenus des devis lus transformés en DTO
     */
    List<Optional<ContenuDevisDTO>> findAll();

    /**
     * Lit un contenuDevis en BDD à partir de son identifiant
     *
     * @param id : identifiant du contenuDevis à lire
     * @return le contenu du devis lu et transformé en DTO
     */
    Optional<ContenuDevisDTO> findById(long id);

    /**
     * Ajoute un contenuDevis en BDD
     *
     * @param ContenuDevisDTO ContenuDevis à ajouter au format DTO
     * @return ContenuDevis ajouté avec son identifiant auto-genéré par la BDD au format DTO
     */
    Optional<ContenuDevisDTO> ajouter(ContenuDevisDTO ContenuDevisDTO);

    /**
     * Modifie un contenuDevis en BDD s'il existe (id renseigné) ou ajoute le contenuDevis en BDD
     *
     * @param ContenuDevisDTO ContenuDevis à modifier au format DTO
     * @return ContenuDevis modifé avec son identifiant auto-genéré par la BDD en cas de création au format DTO
     */
    Optional<ContenuDevisDTO> modifier(ContenuDevisDTO ContenuDevisDTO);

    /**
     * Supprime un contenuDevis en BDD
     *
     * @param ContenuDevisDTO ContenuDevis à supprimer au format DTO
     */
    void supprimer(ContenuDevisDTO ContenuDevisDTO);

    void createFromPanier(DevisDTO devisDTO, PanierDTO panierDTO);

    List<Optional<ContenuDevisDTO>> findAllByDevis(DevisDTO devisDTO);
}