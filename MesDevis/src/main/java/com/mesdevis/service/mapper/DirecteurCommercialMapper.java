package com.mesdevis.service.mapper;

import com.mesdevis.entity.DirecteurCommercial;
import com.mesdevis.entity.dto.DirecteurCommercialDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DirecteurCommercialMapper {

    private static final Logger log = LoggerFactory.getLogger(DirecteurCommercialMapper.class);

    public DirecteurCommercialDTO toDto (DirecteurCommercial directeurCommercial) {
        if (directeurCommercial == null) { return null; }
        DirecteurCommercialDTO directeurCommercialDTO = new DirecteurCommercialDTO();

        directeurCommercialDTO.setId(directeurCommercial.getId());
        directeurCommercialDTO.setUtilisateur(directeurCommercial.getUtilisateur());

        return directeurCommercialDTO;
    }

    public DirecteurCommercial toEntity (DirecteurCommercialDTO directeurCommercialDTO) {
        if (directeurCommercialDTO == null) {
            return null;
        }
        DirecteurCommercial directeurCommercial = new DirecteurCommercial(directeurCommercialDTO.getId(), directeurCommercialDTO.getUtilisateur());
        return directeurCommercial;
    }
}
