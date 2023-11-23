package com.mesdevis.controller;

import com.mesdevis.entity.dto.ContenuDevisDTO;
import com.mesdevis.service.ContenuDevisService;
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
public class ContenuDevisController {

    private static final Logger log = LoggerFactory.getLogger(ContenuDevisController.class);

    @Autowired
    private ContenuDevisService contenuDevisService;

    @GetMapping(value = "/contenuDevis", produces = "application/json")
    public ResponseEntity<List<ContenuDevisDTO>> getContenuDeviss() {
        List<ContenuDevisDTO> resultList = new ArrayList<>();
        List<Optional<ContenuDevisDTO>> contenuDevisList = contenuDevisService.findAll();
        for (Optional<ContenuDevisDTO> contenuDevisDTO : contenuDevisList) {
            if (contenuDevisDTO.isPresent()) {
                resultList.add(contenuDevisDTO.get());
            }
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping(value = "/contenuDevis/{id}", produces = "application/json")
    public ResponseEntity<ContenuDevisDTO> getContenuDevis(@PathVariable long id) {
        Optional<ContenuDevisDTO> contenuDevis = contenuDevisService.findById(id);
        if (contenuDevis.isPresent()) {
            return ResponseEntity.ok(contenuDevis.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/contenuDevis", produces = "application/json")
    public ResponseEntity<ContenuDevisDTO> createContenuDevis(@RequestBody ContenuDevisDTO contenuDevis) {
        if (contenuDevis == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<ContenuDevisDTO> result = contenuDevisService.ajouter(contenuDevis);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/contenuDevis", produces = "application/json")
    public ResponseEntity<ContenuDevisDTO> updateContenuDevis(@RequestBody ContenuDevisDTO contenuDevis) {
        if (contenuDevis == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<ContenuDevisDTO> result = contenuDevisService.modifier(contenuDevis);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/contenuDevis/{id}", produces = "application/json")
    public ResponseEntity<ContenuDevisDTO> deleteContenuDevis(@PathVariable long id) {
        Optional<ContenuDevisDTO> contenuDevisDTO = contenuDevisService.findById(id);
        if (contenuDevisDTO.isPresent()) {
            contenuDevisService.supprimer(contenuDevisDTO.get());
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}