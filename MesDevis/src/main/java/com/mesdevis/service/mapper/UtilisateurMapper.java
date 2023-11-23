package com.mesdevis.service.mapper;

import com.mesdevis.entity.Utilisateur;
import com.mesdevis.entity.dto.UtilisateurDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurMapper {

    private static final Logger log = LoggerFactory.getLogger(UtilisateurMapper.class);

    public UtilisateurDTO toDto (Utilisateur utilisateur) {
        if (utilisateur == null) { return null; }
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();

        utilisateurDTO.setId(utilisateur.getId());
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setPrenom(utilisateur.getPrenom());

        return utilisateurDTO;
    }

    public Utilisateur toEntity (UtilisateurDTO utilisateurDTO) {
        if (utilisateurDTO == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur(utilisateurDTO.getId(), utilisateurDTO.getNom(), utilisateurDTO.getPrenom());

        return utilisateur;
    }
}
