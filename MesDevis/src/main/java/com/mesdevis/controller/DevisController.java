package com.mesdevis.controller;

import com.mesdevis.entity.dto.ContenuDevisDTO;
import com.mesdevis.entity.dto.DevisDTO;
import com.mesdevis.service.ContenuDevisService;
import com.mesdevis.service.DevisService;
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
public class DevisController {

    private static final Logger log = LoggerFactory.getLogger(DevisController.class);

    @Autowired
    private DevisService devisService;
    @Autowired
    private ContenuDevisService contenuDevisService;

    @GetMapping(value = "/devis", produces = "application/json")
    public ResponseEntity<List<DevisDTO>> getDeviss() {
        List<DevisDTO> resultList = new ArrayList<>();
        List<Optional<DevisDTO>> devisList = devisService.findAll();
        for (Optional<DevisDTO> devisDTO : devisList) {
            if (devisDTO.isPresent()) {
                resultList.add(devisDTO.get());
            }
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping(value = "/devis/{id}", produces = "application/json")
    public ResponseEntity<DevisDTO> getDevis(@PathVariable long id) {
        Optional<DevisDTO> devis = devisService.findById(id);
        if (devis.isPresent()) {
            return ResponseEntity.ok(devis.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/devis", produces = "application/json")
    public ResponseEntity<DevisDTO> createDevis(@RequestBody DevisDTO devis) {
        if (devis == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<DevisDTO> result = devisService.ajouter(devis);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/devis", produces = "application/json")
    public ResponseEntity<DevisDTO> updateDevis(@RequestBody DevisDTO devis) {
        if (devis == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<DevisDTO> result = devisService.modifier(devis);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/devis/{id}", produces = "application/json")
    public ResponseEntity<DevisDTO> deleteDevis(@PathVariable long id) {
        Optional<DevisDTO> devisDTO = devisService.findById(id);
        if (devisDTO.isPresent()) {
            devisService.supprimer(devisDTO.get());
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Méthode permettant de calculer le montant d'un devis HT
     * @param id : l'id du devis
     * @return le montant du devis
     */
    @GetMapping(value = "/devis/getMontant/{id}", produces = "application/json")
    public float calculerMontantDevis(@PathVariable long id) {
        float prixTotal = 0;
        Optional<DevisDTO> devis = devisService.findById(id);
        if (devis.isPresent()) {
            List<Optional<ContenuDevisDTO>> contenuDevisList = contenuDevisService.findAllByDevis(devis.get());
            for (Optional<ContenuDevisDTO> contenuDevisDTO : contenuDevisList) {
                if (contenuDevisDTO.isPresent()) {
                    prixTotal += contenuDevisDTO.get().getArticle().getPrixHT() * contenuDevisDTO.get().getQuantiteArticles();
                }
            }
        }
        return prixTotal;
    }
}