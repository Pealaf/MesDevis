package com.mesdevis.service;

import com.mesdevis.entity.Devis;
import com.mesdevis.entity.dto.DevisDTO;
import com.mesdevis.entity.dto.UtilisateurDTO;
import com.mesdevis.repository.DevisRepository;
import com.mesdevis.service.mapper.UtilisateurMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class DevisServiceTest {

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private DevisService devisService;
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Test
    public void devisService_findAllTest(){
        Optional<UtilisateurDTO> utilisateurDTO = utilisateurService.findById(1);
        // GIVEN
        Devis devis1 = new Devis(false, false, utilisateurMapper.toEntity(utilisateurDTO.get()));
        devisRepository.save(devis1);
        Devis devis2 = new Devis(false, false, utilisateurMapper.toEntity(utilisateurDTO.get()));
        devisRepository.save(devis2);

        // WHEN
        List<Optional<DevisDTO>> optionalList = devisService.findAll();
        // THEN
        Assert.notNull(optionalList, "liste lue nulle");
        Assert.notEmpty(optionalList, "liste lue vide");
        Assert.isTrue(optionalList.size() >= 2, "La taille de la liste est au moins de 2 enregistrements");
    }

    @Test
    public void devisService_findbyId_1Test(){
        // WHEN
        Optional<DevisDTO> devis = devisService.findById(2);
        // THEN
        Assert.notNull(devis, "devis 1 lu null");
        Assert.isTrue(devis.isPresent(), "Le devis 1 est bien lu");
    }

    @Test
    public void devisService_findbyIdMinus1Test(){
        // WHEN
        Optional<DevisDTO> devis = devisService.findById(-1);
        // THEN
        Assert.notNull(devis, "devis -1 lu null");
        Assert.isTrue(devis.isEmpty(), "Le devis -1 est bien vide");
    }
}
