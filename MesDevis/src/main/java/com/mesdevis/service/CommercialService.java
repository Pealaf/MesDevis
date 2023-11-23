package com.mesdevis.service;

import com.mesdevis.entity.dto.CommercialDTO;

import java.util.List;
import java.util.Optional;

public interface CommercialService {

    /**
     * Lit tous les commerciaux depuis la BDD
     * @return liste des commerciaux lus transformés en DTO
     */
    List<Optional<CommercialDTO>> findAll();

    /**
     * Lit un commercial en BDD à partir de son identifiant
     * @param id : identifiant du commercial à lire
     * @return le commercial lu et transformé en DTO
     */
    Optional<CommercialDTO> findById(long id);

    /**
     * Ajoute un commercial en BDD
     * @param commercialDTO Commercial à ajouter au format DTO
     * @return Commercial ajouté avec son identifiant auto-genéré par la BDD au format DTO
     */
    Optional<CommercialDTO> ajouter(CommercialDTO commercialDTO);

    /**
     * Modifie un commercial en BDD s'il existe (id renseigné) ou ajoute le commercial en BDD
     * @param commercialDTO commercial à modifier au format DTO
     * @return Commercial modifé avec son identifiant auto-genéré par la BDD en cas de création au format DTO
     */
    Optional<CommercialDTO> modifier(CommercialDTO commercialDTO);

    /**
     * Supprime un Commercial en BDD
     * @param commercialDTO Commercial à supprimer au format DTO
     */
    void supprimer(CommercialDTO commercialDTO);
}
