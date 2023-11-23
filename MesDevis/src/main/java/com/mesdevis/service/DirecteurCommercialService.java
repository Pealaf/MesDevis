package com.mesdevis.service;

import com.mesdevis.entity.dto.DirecteurCommercialDTO;

import java.util.List;
import java.util.Optional;

public interface DirecteurCommercialService {

    /**
     * Lit tous les directeurs commerciaux depuis la BDD
     * @return liste des directeurs commerciaux lus transformés en DTO
     */
    List<Optional<DirecteurCommercialDTO>> findAll();

    /**
     * Lit un directeurCommercial en BDD à partir de son identifiant
     * @param id : identifiant du directeurCommercial à lire
     * @return le directeurCommercial lu et transformé en DTO
     */
    Optional<DirecteurCommercialDTO> findById(long id);

    /**
     * Ajoute un directeurCommercial en BDD
     * @param DirecteurCommercialDTO DirecteurCommercial à ajouter au format DTO
     * @return DirecteurCommercial ajouté avec son identifiant auto-genéré par la BDD au format DTO
     */
    Optional<DirecteurCommercialDTO> ajouter(DirecteurCommercialDTO DirecteurCommercialDTO);

    /**
     * Modifie un directeurCommercial en BDD s'il existe (id renseigné) ou ajoute le directeurCommercial en BDD
     * @param DirecteurCommercialDTO DirecteurCommercial à modifier au format DTO
     * @return DirecteurCommercial modifé avec son identifiant auto-genéré par la BDD en cas de création au format DTO
     */
    Optional<DirecteurCommercialDTO> modifier(DirecteurCommercialDTO DirecteurCommercialDTO);

    /**
     * Supprime un directeurCommercial en BDD
     * @param DirecteurCommercialDTO DirecteurCommercial à supprimer au format DTO
     */
    void supprimer(DirecteurCommercialDTO DirecteurCommercialDTO);
}
