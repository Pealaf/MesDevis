package com.mesdevis.service.mapper;

import com.mesdevis.entity.Commercial;
import com.mesdevis.entity.dto.CommercialDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CommercialMapper {

    private static final Logger log = LoggerFactory.getLogger(CommercialMapper.class);

    public CommercialDTO toDto (Commercial commercial) {
        if (commercial == null) { return null; }
        CommercialDTO commercialDTO = new CommercialDTO();
        commercialDTO.setId(commercial.getId());
        commercialDTO.setUtilisateur(commercial.getUtilisateur());

        return commercialDTO;
    }

    public Commercial toEntity (CommercialDTO commercialDTO) {
        if (commercialDTO == null) {
            return null;
        }
        Commercial commercial = new Commercial(commercialDTO.getId(), commercialDTO.getUtilisateur());

        return commercial;
    }
}
