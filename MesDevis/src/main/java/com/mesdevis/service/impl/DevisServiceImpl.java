package com.mesdevis.service.impl;

import com.mesdevis.entity.Devis;
import com.mesdevis.entity.dto.DevisDTO;
import com.mesdevis.entity.dto.UtilisateurDTO;
import com.mesdevis.repository.DevisRepository;
import com.mesdevis.service.DevisService;
import com.mesdevis.service.mapper.DevisMapper;
import com.mesdevis.service.mapper.UtilisateurMapper;
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
public class DevisServiceImpl implements DevisService {

    private static final Logger log = LoggerFactory.getLogger(DevisServiceImpl.class);

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private DevisMapper devisMapper;
    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Optional<DevisDTO>> findAll() {
        log.info("find all Devis");
        List<Optional<DevisDTO>> resultList = new ArrayList<>();
        List<Devis> DevisList = devisRepository.findAll();
        for (Devis devis : DevisList) {
            if (devis != null) {
                resultList.add(Optional.of(devisMapper.toDto(devis)));
            }
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DevisDTO> findById(long id) {
        log.info("find Devis by Id");
        try {
            Optional<Devis> devis = devisRepository.findById(id);
            if (devis.isPresent()) {
                return Optional.of(devisMapper.toDto(devis.get()));
            }
        } catch (Exception e) {
            log.info("Devis with id {} not found", id);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<DevisDTO> ajouter(DevisDTO devisDTO) {
        Devis devis = devisRepository.saveAndFlush(devisMapper.toEntity(devisDTO));
        return Optional.of(devisMapper.toDto(devis));
    }

    @Override
    @Transactional
    public Optional<DevisDTO> modifier(DevisDTO devisDTO) {
        Devis devis = devisRepository.saveAndFlush(devisMapper.toEntity(devisDTO));
        return Optional.of(devisMapper.toDto(devis));
    }

    @Override
    @Transactional
    public void supprimer(DevisDTO devisDTO) {
        devisRepository.deleteById(devisDTO.getId());
    }

    @Override
    @Transactional
    public Optional<DevisDTO> genererDevis(UtilisateurDTO utilisateurDTO) {
        Devis devis = new Devis(false, false, utilisateurMapper.toEntity(utilisateurDTO));
        devis = devisRepository.saveAndFlush(devis);
        return Optional.of(devisMapper.toDto(devis));
    }
}
