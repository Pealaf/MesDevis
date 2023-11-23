package com.mesdevis.service;

import com.mesdevis.entity.Utilisateur;
import com.mesdevis.entity.dto.UtilisateurDTO;
import com.mesdevis.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class UtilisateurServiceTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    @Test
    public void utilisateurService_findAllTest(){
        // GIVEN
        Utilisateur utilisateur1 = new Utilisateur("NomN°1", "PrenomN°1");
        utilisateurRepository.save(utilisateur1);
        Utilisateur utilisateur2 = new Utilisateur("NomN°2", "PrenomN°2");
        utilisateurRepository.save(utilisateur2);

        // WHEN
        List<Optional<UtilisateurDTO>> optionalList = utilisateurService.findAll();
        // THEN
        Assert.notNull(optionalList, "liste lue nulle");
        Assert.notEmpty(optionalList, "liste lue vide");
        Assert.isTrue(optionalList.size() >= 2, "La taille de la liste est au moins de 2 enregistrements");
    }

    @Test
    public void utilisateurService_findbyId_1Test(){
        // WHEN
        Optional<UtilisateurDTO> utilisateur = utilisateurService.findById(1);
        // THEN
        Assert.notNull(utilisateur, "utilisateur 1 lu null");
        Assert.isTrue(utilisateur.isPresent(), "L'utilisateur 1 est bien lu");
    }

    @Test
    public void utilisateurService_findbyIdMinus1Test(){
        // WHEN
        Optional<UtilisateurDTO> utilisateur = utilisateurService.findById(-1);
        // THEN
        Assert.notNull(utilisateur, "utilisateur -1 lu null");
        Assert.isTrue(utilisateur.isEmpty(), "L'utilisateur -1 est bien vide");
    }
}