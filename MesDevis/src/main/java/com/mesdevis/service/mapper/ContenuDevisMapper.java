package com.mesdevis.service.mapper;

import com.mesdevis.entity.ContenuDevis;
import com.mesdevis.entity.dto.ContenuDevisDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContenuDevisMapper {

    private static final Logger log = LoggerFactory.getLogger(ContenuDevisMapper.class);

    public ContenuDevisDTO toDto (ContenuDevis contenuDevis) {
        if (contenuDevis == null) { return null; }
        ContenuDevisDTO contenuDevisDTO = new ContenuDevisDTO();

        contenuDevisDTO.setId(contenuDevis.getId());
        contenuDevisDTO.setDevis(contenuDevis.getDevis());
        contenuDevisDTO.setArticle(contenuDevis.getArticle());
        contenuDevisDTO.setQuantiteArticles(contenuDevis.getQuantiteArticles());

        return contenuDevisDTO;
    }

    public ContenuDevis toEntity (ContenuDevisDTO contenuDevisDTO) {
        if (contenuDevisDTO == null) {
            return null;
        }
        ContenuDevis contenuDevis = new ContenuDevis(contenuDevisDTO.getId(), contenuDevisDTO.getDevis(), contenuDevisDTO.getArticle(), contenuDevisDTO.getQuantiteArticles());
        return contenuDevis;
    }
}
