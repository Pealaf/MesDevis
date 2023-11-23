package com.mesdevis.service.impl;

import com.mesdevis.entity.DirecteurCommercial;
import com.mesdevis.entity.dto.DirecteurCommercialDTO;
import com.mesdevis.repository.DirecteurCommercialRepository;
import com.mesdevis.service.DirecteurCommercialService;
import com.mesdevis.service.mapper.DirecteurCommercialMapper;
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
public class DirecteurCommercialServiceImpl implements DirecteurCommercialService {

    private static final Logger log = LoggerFactory.getLogger(DirecteurCommercialServiceImpl.class);

    @Autowired
    private DirecteurCommercialRepository directeurCommercialRepository;

    @Autowired
    private DirecteurCommercialMapper directeurCommercialMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Optional<DirecteurCommercialDTO>> findAll() {
        log.info("find all DirecteurCommercial");
        List<Optional<DirecteurCommercialDTO>> resultList = new ArrayList<>();
        List<DirecteurCommercial> DirecteurCommercialList = directeurCommercialRepository.findAll();
        for (DirecteurCommercial directeurCommercial : DirecteurCommercialList) {
            if (directeurCommercial != null) {
                resultList.add(Optional.of(directeurCommercialMapper.toDto(directeurCommercial)));
            }
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DirecteurCommercialDTO> findById(long id) {
        log.info("find DirecteurCommercial by Id");
        try {
            Optional<DirecteurCommercial> directeurCommercial = directeurCommercialRepository.findById(id);
            if (directeurCommercial.isPresent()) {
                return Optional.of(directeurCommercialMapper.toDto(directeurCommercial.get()));
            }
        } catch (Exception e) {
            log.info("DirecteurCommercial with id {} not found", id);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<DirecteurCommercialDTO> ajouter(DirecteurCommercialDTO directeurCommercialDTO) {
        DirecteurCommercial directeurCommercial = directeurCommercialRepository.saveAndFlush(directeurCommercialMapper.toEntity(directeurCommercialDTO));
        return Optional.of(directeurCommercialMapper.toDto(directeurCommercial));
    }

    @Override
    @Transactional
    public Optional<DirecteurCommercialDTO> modifier(DirecteurCommercialDTO directeurCommercialDTO) {
        DirecteurCommercial directeurCommercial = directeurCommercialRepository.saveAndFlush(directeurCommercialMapper.toEntity(directeurCommercialDTO));
        return Optional.of(directeurCommercialMapper.toDto(directeurCommercial));
    }

    @Override
    @Transactional
    public void supprimer(DirecteurCommercialDTO directeurCommercialDTO) {
        directeurCommercialRepository.deleteById(directeurCommercialDTO.getId());
    }
}