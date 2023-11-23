package com.mesdevis.service.impl;

import com.mesdevis.entity.Commercial;
import com.mesdevis.entity.dto.CommercialDTO;
import com.mesdevis.repository.CommercialRepository;
import com.mesdevis.service.CommercialService;
import com.mesdevis.service.mapper.CommercialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommercialServiceImpl implements CommercialService {

    private static final Logger log = LoggerFactory.getLogger(CommercialServiceImpl.class);

    @Autowired
    private CommercialRepository commercialRepository;

    @Autowired
    private CommercialMapper commercialMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Optional<CommercialDTO>> findAll() {
        log.info("find all Commercial");
        List<Optional<CommercialDTO>> resultList = new ArrayList<>();
        List<Commercial> commercialList = commercialRepository.findAll();
        for (Commercial commercial : commercialList) {
            if (commercial != null) {
                resultList.add(Optional.of(commercialMapper.toDto(commercial)));
            }
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommercialDTO> findById(long id) {
        log.info("find Commercial by Id");
        try {
            Optional<Commercial> commercial = commercialRepository.findById(id);
            if (commercial.isPresent()) {
                return Optional.of(commercialMapper.toDto(commercial.get()));
            }
        } catch (Exception e) {
            log.info("Commercial with id {} not found", id);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<CommercialDTO> ajouter(CommercialDTO commercialDTO) {
        Commercial commercial = commercialRepository.saveAndFlush(commercialMapper.toEntity(commercialDTO));
        return Optional.of(commercialMapper.toDto(commercial));
    }

    @Override
    @Transactional
    public Optional<CommercialDTO> modifier(CommercialDTO commercialDTO) {
        Commercial commercial = commercialRepository.saveAndFlush(commercialMapper.toEntity(commercialDTO));
        return Optional.of(commercialMapper.toDto(commercial));
    }

    @Override
    @Transactional
    public void supprimer(CommercialDTO commercialDTO) {
        commercialRepository.deleteById(commercialDTO.getUtilisateur().getId());
    }
}