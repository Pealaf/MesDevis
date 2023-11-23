package com.mesdevis.service.mapper;

import com.mesdevis.entity.Panier;
import com.mesdevis.entity.dto.PanierDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PanierMapper {

    private static final Logger log = LoggerFactory.getLogger(PanierMapper.class);

    public PanierDTO toDto (Panier panier) {
        if (panier == null) { return null; }
        PanierDTO panierDTO = new PanierDTO();

        panierDTO.setId(panier.getId());
        panierDTO.setUtilisateur(panier.getUtilisateur());
        panierDTO.setArticle(panier.getArticle());
        panierDTO.setQuantiteArticles(panier.getQuantiteArticles());

        return panierDTO;
    }

    public Panier toEntity (PanierDTO panierDTO) {
        if (panierDTO == null) {
            return null;
        }
        Panier panier = new Panier(panierDTO.getId(), panierDTO.getUtilisateur(), panierDTO.getArticle(), panierDTO.getQuantiteArticles());
        return panier;
    }
}
