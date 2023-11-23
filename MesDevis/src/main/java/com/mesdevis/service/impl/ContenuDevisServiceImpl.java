package com.mesdevis.service.impl;

import com.mesdevis.entity.ContenuDevis;
import com.mesdevis.entity.dto.ContenuDevisDTO;
import com.mesdevis.entity.dto.DevisDTO;
import com.mesdevis.entity.dto.PanierDTO;
import com.mesdevis.repository.ContenuDevisRepository;
import com.mesdevis.service.ContenuDevisService;
import com.mesdevis.service.mapper.ContenuDevisMapper;
import com.mesdevis.service.mapper.DevisMapper;
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
public class ContenuDevisServiceImpl implements ContenuDevisService {

    private static final Logger log = LoggerFactory.getLogger(ContenuDevisServiceImpl.class);

    @Autowired
    private ContenuDevisRepository contenuDevisRepository;

    @Autowired
    private ContenuDevisMapper contenuDevisMapper;
    @Autowired
    private DevisMapper devisMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Optional<ContenuDevisDTO>> findAll() {
        log.info("find all ContenuDevis");
        List<Optional<ContenuDevisDTO>> resultList = new ArrayList<>();
        List<ContenuDevis> ContenuDevisList = contenuDevisRepository.findAll();
        for (ContenuDevis contenuDevis : ContenuDevisList) {
            if (contenuDevis != null) {
                resultList.add(Optional.of(contenuDevisMapper.toDto(contenuDevis)));
            }
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContenuDevisDTO> findById(long id) {
        log.info("find ContenuDevis by Id");
        try {
            Optional<ContenuDevis> contenuDevis = contenuDevisRepository.findById(id);
            if (contenuDevis.isPresent()) {
                return Optional.of(contenuDevisMapper.toDto(contenuDevis.get()));
            }
        } catch (Exception e) {
            log.info("ContenuDevis with id {} not found", id);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<ContenuDevisDTO> ajouter(ContenuDevisDTO contenuDevisDTO) {
        ContenuDevis contenuDevis = contenuDevisRepository.saveAndFlush(contenuDevisMapper.toEntity(contenuDevisDTO));
        return Optional.of(contenuDevisMapper.toDto(contenuDevis));
    }

    @Override
    @Transactional
    public Optional<ContenuDevisDTO> modifier(ContenuDevisDTO contenuDevisDTO) {
        ContenuDevis contenuDevis = contenuDevisRepository.saveAndFlush(contenuDevisMapper.toEntity(contenuDevisDTO));
        return Optional.of(contenuDevisMapper.toDto(contenuDevis));
    }

    @Override
    @Transactional
    public void supprimer(ContenuDevisDTO contenuDevisDTO) {
        contenuDevisRepository.deleteById(contenuDevisDTO.getId());
    }

    @Override
    @Transactional
    public void createFromPanier(DevisDTO devisDTO, PanierDTO panierDTO) {
        ContenuDevis contenuDevis = new ContenuDevis(devisMapper.toEntity(devisDTO), panierDTO.getArticle(), panierDTO.getQuantiteArticles());
        contenuDevisRepository.saveAndFlush(contenuDevis);
    }

    @Override
    @Transactional
    public List<Optional<ContenuDevisDTO>> findAllByDevis(DevisDTO devisDTO) {
        log.info("find all ContenuDevis by Devis");
        List<Optional<ContenuDevisDTO>> resultList = new ArrayList<>();
        Optional<List<ContenuDevis>> contenuDevisList = contenuDevisRepository.findAllByDevis(devisMapper.toEntity(devisDTO));
        if(contenuDevisList.isPresent()) {
            for (ContenuDevis contenuDevis : contenuDevisList.get()) {
                if (contenuDevis != null) {
                    resultList.add(Optional.of(contenuDevisMapper.toDto(contenuDevis)));
                }
            }
        }
        return resultList;
    }
}
