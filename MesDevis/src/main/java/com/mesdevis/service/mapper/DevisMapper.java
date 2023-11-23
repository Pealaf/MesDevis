package com.mesdevis.service.mapper;

import com.mesdevis.entity.Devis;
import com.mesdevis.entity.dto.DevisDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DevisMapper {

    private static final Logger log = LoggerFactory.getLogger(DevisMapper.class);

    public DevisDTO toDto (Devis devis) {
        if (devis == null) { return null; }
        DevisDTO devisDTO = new DevisDTO();

        devisDTO.setId(devis.getId());
        devisDTO.setValidation(devis.isValidation());
        devisDTO.setPaiement(devis.isPaiement());
        devisDTO.setUtilisateur(devis.getUtilisateur());

        return devisDTO;
    }

    public Devis toEntity (DevisDTO devisDTO) {
        if (devisDTO == null) {
            return null;
        }
        Devis devis = new Devis(devisDTO.getId(), devisDTO.isValidation(), devisDTO.isPaiement(), devisDTO.getUtilisateur());
        return devis;
    }
}
