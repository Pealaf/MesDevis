package com.mesdevis.service.impl;

import com.mesdevis.entity.Panier;
import com.mesdevis.entity.dto.PanierDTO;
import com.mesdevis.entity.dto.UtilisateurDTO;
import com.mesdevis.repository.PanierRepository;
import com.mesdevis.service.PanierService;
import com.mesdevis.service.mapper.PanierMapper;
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
public class PanierServiceImpl implements PanierService {

    private static final Logger log = LoggerFactory.getLogger(PanierServiceImpl.class);

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private PanierMapper panierMapper;
    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Optional<PanierDTO>> findAll() {
        log.info("find all Panier");
        List<Optional<PanierDTO>> resultList = new ArrayList<>();
        List<Panier> panierList = panierRepository.findAll();
        for (Panier panier : panierList) {
            if (panier != null) {
                resultList.add(Optional.of(panierMapper.toDto(panier)));
            }
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PanierDTO> findById(long id) {
        log.info("find Panier by Id");
        try {
            Optional<Panier> panier = panierRepository.findById(id);
            if (panier.isPresent()) {
                return Optional.of(panierMapper.toDto(panier.get()));
            }
        } catch (Exception e) {
            log.info("Panier with id {} not found", id);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<PanierDTO> ajouter(PanierDTO panierDTO) {
        Panier panier = panierRepository.saveAndFlush(panierMapper.toEntity(panierDTO));
        return Optional.of(panierMapper.toDto(panier));
    }

    @Override
    @Transactional
    public Optional<PanierDTO> modifier(PanierDTO panierDTO) {
        Panier panier = panierRepository.saveAndFlush(panierMapper.toEntity(panierDTO));
        return Optional.of(panierMapper.toDto(panier));
    }

    @Override
    @Transactional
    public void supprimer(PanierDTO panierDTO) {
        panierRepository.deleteById(panierDTO.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Optional<PanierDTO>> findAllByUtilisateur(UtilisateurDTO utilisateurDTO) {
        log.info("find all Panier by User");
        List<Optional<PanierDTO>> resultList = new ArrayList<>();
        Optional<List<Panier>> panierList = panierRepository.findAllByUtilisateur(utilisateurMapper.toEntity(utilisateurDTO));
        if(panierList.isPresent()) {
            for (Panier panier : panierList.get()) {
                if (panier != null) {
                    resultList.add(Optional.of(panierMapper.toDto(panier)));
                }
            }
        }
        return resultList;
    }
}