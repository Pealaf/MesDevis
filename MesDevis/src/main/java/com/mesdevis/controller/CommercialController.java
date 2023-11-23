package com.mesdevis.controller;

import com.mesdevis.entity.dto.CommercialDTO;
import com.mesdevis.entity.dto.ContenuDevisDTO;
import com.mesdevis.entity.dto.DevisDTO;
import com.mesdevis.service.CommercialService;
import com.mesdevis.service.ContenuDevisService;
import com.mesdevis.service.DevisService;
import com.mesdevis.service.mapper.CommercialMapper;
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
public class CommercialController {

    private static final Logger log = LoggerFactory.getLogger(CommercialController.class);

    @Autowired
    private CommercialService commercialService;
    @Autowired
    private ContenuDevisService contenuDevisService;
    @Autowired
    private DevisService devisService;

    @Autowired
    private CommercialMapper commercialMapper;

    @GetMapping(value = "/commercials", produces = "application/json")
    public ResponseEntity<List<CommercialDTO>> getCommercials() {
        List<CommercialDTO> resultList = new ArrayList<>();
        List<Optional<CommercialDTO>> commercialList = commercialService.findAll();
        for (Optional<CommercialDTO> commercialDTO : commercialList) {
            if (commercialDTO.isPresent()) {
                resultList.add(commercialDTO.get());
            }
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping(value = "/commercial/{id}", produces = "application/json")
    public ResponseEntity<CommercialDTO> getCommercial(@PathVariable long id) {
        Optional<CommercialDTO> commercial = commercialService.findById(id);
        if (commercial.isPresent()) {
            return ResponseEntity.ok(commercial.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/commercial", produces = "application/json")
    public ResponseEntity<CommercialDTO> createCommercial(@RequestBody CommercialDTO commercial) {
        if (commercial == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<CommercialDTO> result = commercialService.ajouter(commercial);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/commercial", produces = "application/json")
    public ResponseEntity<CommercialDTO> updateCommercial(@RequestBody CommercialDTO commercial) {
        if (commercial == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<CommercialDTO> result = commercialService.modifier(commercial);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/commercial/{id}", produces = "application/json")
    public ResponseEntity<CommercialDTO> deleteCommercial(@PathVariable long id) {
        Optional<CommercialDTO> commercialDTO = commercialService.findById(id);
        if (commercialDTO.isPresent()) {
            commercialService.supprimer(commercialDTO.get());
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Méthode permettant de calculer le montant d'un devis
     * @param devisDTO
     * @return le montant du devis
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
     * Méthode permettant au commercial de valider un devis
     * @param idCommercial
     * @param idDevis
     * @return
     */
    @PutMapping(value = "/commercial/validerDevis/{idCommercial}/{idDevis}", produces = "application/json")
    public String validerDevis(@PathVariable long idCommercial, @PathVariable long idDevis) {
        // Récupération du validateur du devis
        ResponseEntity<CommercialDTO> validateur = getValidateur(idDevis);
        // Récupération du commercial qui effectue la validation
        Optional<CommercialDTO> commercial = commercialService.findById(idCommercial);
        // S'il s'agit de la même personne
        if (commercial.isPresent() && commercial.get().equals(validateur.getBody())) {
            // Récupération du devis
            Optional<DevisDTO> devis = devisService.findById(idDevis);
            // Si le devis existe bien
            if (devis.isPresent()) {
                DevisDTO devisDTO = devis.get();
                // Récupération du prix total du devis
                float prixTotal = calculerMontantDevis(devisDTO);
                // Si le prix du devis est supérieur à 10000€ et qu'il n'est pas encore validé
                if (prixTotal < 10000 && !devisDTO.isValidation()) {
                    // Validation du devis
                    devisDTO.setValidation(true);
                    // Mise à jour du devis en BDD
                    devisService.modifier(devisDTO);
                    return "Le devis a été validé.";
                }
            }
        }
        return "Une erreur est survenue lors de la validation du devis.";
    }

    /**
     * Méthode permettant de savoir quel commercial peut valider un devis en fonction du poids des articles.
     * Le commercial le plus apte à valider un devis est celui dont la somme des articles qu'il gère est la plus élevée.
     * @param idDevis
     * @return
     */
    @GetMapping(value = "/commercial/getValidateur/{idDevis}", produces = "application/json")
    public ResponseEntity<CommercialDTO> getValidateur(@PathVariable long idDevis) {
        // Récupération du devis
        Optional<DevisDTO> devis = devisService.findById(idDevis);
        // Si le devis existe bien
        if (devis.isPresent()) {
            List<Optional<CommercialDTO>> commercialList = commercialService.findAll();
            float[] tableauMontants = new float[commercialList.size()];

            List<Optional<ContenuDevisDTO>> contenuDevisList = contenuDevisService.findAllByDevis(devis.get());
            for (Optional<ContenuDevisDTO> contenuDevisDTO : contenuDevisList) {
                if (contenuDevisDTO.isPresent()) {
                    // Récupération de l'index du commercial correspondant à la catégorie de l'article
                    int index = commercialList.indexOf(Optional.of(commercialMapper.toDto(contenuDevisDTO.get().getArticle().getCategorie().getCommercial())));
                    tableauMontants[index] += contenuDevisDTO.get().getArticle().getPrixHT() * contenuDevisDTO.get().getQuantiteArticles();
                }
            }

            float max = 0f;
            int index = 0;
            for(int i=0;i<tableauMontants.length;i++) {
                if(max < tableauMontants[i]) {
                    max= tableauMontants[i];
                    index = i;
                }
            }
            return ResponseEntity.ok(commercialList.get(index).get());
        }
        return ResponseEntity.notFound().build();
    }
}