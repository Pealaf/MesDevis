package com.mesdevis.service;

import com.mesdevis.entity.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    /**
     * Lit tous les clients depuis la BDD
     * @return liste des clients lus transformés en DTO
     */
    List<Optional<ClientDTO>> findAll();

    /**
     * Lit un client en BDD à partir de son identifiant
     * @param id : identifiant du client à lire
     * @return le client lu et transformé en DTO
     */
    Optional<ClientDTO> findById(long id);

    /**
     * Ajoute un client en BDD
     * @param ClientDTO Client à ajouter au format DTO
     * @return Client ajouté avec son identifiant auto-genéré par la BDD au format DTO
     */
    Optional<ClientDTO> ajouter(ClientDTO ClientDTO);

    /**
     * Modifie un client en BDD s'il existe (id renseigné) ou ajoute le client en BDD
     * @param ClientDTO Client à modifier au format DTO
     * @return Client modifé avec son identifiant auto-genéré par la BDD en cas de création au format DTO
     */
    Optional<ClientDTO> modifier(ClientDTO ClientDTO);

    /**
     * Supprime un client en BDD
     * @param ClientDTO Client à supprimer au format DTO
     */
    void supprimer(ClientDTO ClientDTO);
}
