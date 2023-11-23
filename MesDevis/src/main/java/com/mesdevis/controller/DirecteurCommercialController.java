package com.mesdevis.controller;

import com.mesdevis.entity.dto.ContenuDevisDTO;
import com.mesdevis.entity.dto.DevisDTO;
import com.mesdevis.entity.dto.DirecteurCommercialDTO;
import com.mesdevis.service.ContenuDevisService;
import com.mesdevis.service.DevisService;
import com.mesdevis.service.DirecteurCommercialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DirecteurCommercialController {

    private static final Logger log = LoggerFactory.getLogger(DirecteurCommercialController.class);

    @Autowired
    private DirecteurCommercialService directeurCommercialService;
    @Autowired
    private DevisService devisService;
    @Autowired
    private ContenuDevisService contenuDevisService;

    @GetMapping(value = "/directeurCommercials", produces = "application/json")
    public ResponseEntity<List<DirecteurCommercialDTO>> getDirecteurCommercials() {
        List<DirecteurCommercialDTO> resultList = new ArrayList<>();
        List<Optional<DirecteurCommercialDTO>> directeurCommercialList = directeurCommercialService.findAll();
        for (Optional<DirecteurCommercialDTO> directeurCommercialDTO : directeurCommercialList) {
            if (directeurCommercialDTO.isPresent()) {
                resultList.add(directeurCommercialDTO.get());
            }
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping(value = "/directeurCommercial/{id}", produces = "application/json")
    public ResponseEntity<DirecteurCommercialDTO> getDirecteurCommercial(@PathVariable long id) {
        Optional<DirecteurCommercialDTO> directeurCommercial = directeurCommercialService.findById(id);
        if (directeurCommercial.isPresent()) {
            return ResponseEntity.ok(directeurCommercial.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/directeurCommercial", produces = "application/json")
    public ResponseEntity<DirecteurCommercialDTO> createDirecteurCommercial(@RequestBody DirecteurCommercialDTO directeurCommercial) {
        if (directeurCommercial == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<DirecteurCommercialDTO> result = directeurCommercialService.ajouter(directeurCommercial);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/directeurCommercial", produces = "application/json")
    public ResponseEntity<DirecteurCommercialDTO> updateDirecteurCommercial(@RequestBody DirecteurCommercialDTO directeurCommercial) {
        if (directeurCommercial == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<DirecteurCommercialDTO> result = directeurCommercialService.modifier(directeurCommercial);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/directeurCommercial/{id}", produces = "application/json")
    public ResponseEntity<DirecteurCommercialDTO> deleteDirecteurCommercial(@PathVariable long id) {
        Optional<DirecteurCommercialDTO> directeurCommercialDTO = directeurCommercialService.findById(id);
        if (directeurCommercialDTO.isPresent()) {
            directeurCommercialService.supprimer(directeurCommercialDTO.get());
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Méthode permettant de calculer le montant d'un devis
     * @param devisDTO
     * @return
     */
    public float calculerMontantDevis(DevisDTO devisDTO) {
        float prixTotal = 0;
        List<Optional<ContenuDevisDTO>> contenuDevisList = contenuDevisService.findAllByDevis(devisDTO);
        for (Optional<ContenuDevisDTO> contenuDevisDTO : contenuDevisList) {
            if (contenuDevisDTO.isPresent()) {
                prixTotal += contenuDevisDTO.get().getArticle().getPrixHT() * contenuDevisDTO.get().getQuantiteArticles();
            }
        }
        return prixTotal;
    }

    /**
     * Méthode permettant au directeur commercial de valider un devis supérieur à 10000€ par son id
     * @return
     */
    @PutMapping(value = "/directeurCommercial/validerDevis/{idDevis}", produces = "application/json")
    public String validerDevis(@PathVariable long idDevis) {
        // Récupération du devis
        Optional<DevisDTO> devis = devisService.findById(idDevis);
        // Si le devis existe bien
        if (devis.isPresent()) {
            DevisDTO devisDTO = devis.get();
            // Récupération du prix total du devis
            float prixTotal = calculerMontantDevis(devisDTO);
            // Si le prix du devis est supérieur à 10000€ et qu'il n'est pas encore validé
            if(prixTotal >= 10000 && !devisDTO.isValidation()) {
                // Validation du devis
                devisDTO.setValidation(true);
                // Mise à jour du devis en BDD
                devisService.modifier(devisDTO);
                return "Le devis a bien été validé";
            }
        }
        return "Une erreur est survenue lors de la validation du devis.";
    }

    /**
     * Méthode permettant au directeur commercial de valider tous les devis supérieurs à 10000€
     * @return
     */
    @PutMapping(value = "/directeurCommercial/validerDevis", produces = "application/json")
    public String validerDevis() {
        // Récupération de tous les devis
        List<Optional<DevisDTO>> listeDevis = devisService.findAll();
        // Pour chaque devis
        for(Optional<DevisDTO> devis : listeDevis) {
            // Si le devis existe bien
            if (devis.isPresent()) {
                DevisDTO devisDTO = devis.get();
                // Récupération du prix total du devis
                float prixTotal = calculerMontantDevis(devisDTO);
                // Si le prix du devis est supérieur à 10000€ et qu'il n'est pas encore validé
                if(prixTotal >= 10000 && !devisDTO.isValidation()) {
                    // Validation du devis
                    devisDTO.setValidation(true);
                    // Mise à jour du devis en BDD
                    devisService.modifier(devisDTO);
                }
            }
        }
        return "Tous les devis supérieurs à 10000€ ont bien été validés.";
    }
}