package com.mesdevis.service.impl;

import com.mesdevis.entity.Utilisateur;
import com.mesdevis.entity.dto.UtilisateurDTO;
import com.mesdevis.repository.DevisRepository;
import com.mesdevis.repository.UtilisateurRepository;
import com.mesdevis.service.UtilisateurService;
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
public class UtilisateurServiceImpl implements UtilisateurService {

    private static final Logger log = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Optional<UtilisateurDTO>> findAll() {
        log.info("find all Utilisateur");
        List<Optional<UtilisateurDTO>> resultList = new ArrayList<>();
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        for (Utilisateur utilisateur : utilisateurList) {
            if (utilisateur != null) {
                resultList.add(Optional.of(utilisateurMapper.toDto(utilisateur)));
            }
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UtilisateurDTO> findById(long id) {
        log.info("find Utilisateur by Id");
        try {
            Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
            if (utilisateur.isPresent()) {
                return Optional.of(utilisateurMapper.toDto(utilisateur.get()));
            }
        } catch (Exception e) {
            log.info("Utilisateur with id {} not found", id);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<UtilisateurDTO> ajouter(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = utilisateurRepository.saveAndFlush(utilisateurMapper.toEntity(utilisateurDTO));
        return Optional.of(utilisateurMapper.toDto(utilisateur));
    }

    @Override
    @Transactional
    public Optional<UtilisateurDTO> modifier(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = utilisateurRepository.saveAndFlush(utilisateurMapper.toEntity(utilisateurDTO));
        return Optional.of(utilisateurMapper.toDto(utilisateur));
    }

    @Override
    @Transactional
    public void supprimer(UtilisateurDTO utilisateurDTO) {
        utilisateurRepository.deleteById(utilisateurDTO.getId());
    }

    /*@Override
    @Transactional
    public boolean validerPanier(UtilisateurDTO utilisateurDTO) {
        // Création du devis non payé et non validé
        Devis devis = new Devis(false, false, utilisateurMapper.toEntity(utilisateurDTO));
        devisRepository.saveAndFlush(devis);

        // Création de chaque article dans la table ContenuDevis
        for(int i=0;i<panierDTO)

            return Optional.of(panierMapper.toDto(panier));

        Panier panier = panierRepository.saveAndFlush(panierMapper.toEntity(panierDTO));
        return Optional.of(panierMapper.toDto(panier));
    }*/
}
