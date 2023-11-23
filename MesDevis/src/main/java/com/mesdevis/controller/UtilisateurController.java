package com.mesdevis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mesdevis.entity.dto.DevisDTO;
import com.mesdevis.entity.dto.PanierDTO;
import com.mesdevis.entity.dto.UtilisateurDTO;
import com.mesdevis.service.ContenuDevisService;
import com.mesdevis.service.DevisService;
import com.mesdevis.service.PanierService;
import com.mesdevis.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UtilisateurController {

    private static final Logger log = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private PanierService panierService;
    @Autowired
    private DevisService devisService;
    @Autowired
    private ContenuDevisService contenuDevisService;

    @GetMapping(value = "/utilisateurs", produces = "application/json")
    public ResponseEntity<List<UtilisateurDTO>> getUtilisateurs() {
        List<UtilisateurDTO> resultList = new ArrayList<>();
        List<Optional<UtilisateurDTO>> utilisateurList = utilisateurService.findAll();
        for (Optional<UtilisateurDTO> utilisateurDTO : utilisateurList) {
            if (utilisateurDTO.isPresent()) {
                resultList.add(utilisateurDTO.get());
            }
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping(value = "/utilisateur/{id}", produces = "application/json")
    public ResponseEntity<UtilisateurDTO> getUtilisateur(@PathVariable long id) {
        Optional<UtilisateurDTO> utilisateur = utilisateurService.findById(id);
        if (utilisateur.isPresent()) {
            return ResponseEntity.ok(utilisateur.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/utilisateur", produces = "application/json")
    public ResponseEntity<UtilisateurDTO> createUtilisateur(@RequestBody UtilisateurDTO utilisateur) {
        if (utilisateur == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<UtilisateurDTO> result = utilisateurService.ajouter(utilisateur);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/utilisateur", produces = "application/json")
    public ResponseEntity<UtilisateurDTO> updateUtilisateur(@RequestBody UtilisateurDTO utilisateur) {
        if (utilisateur == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<UtilisateurDTO> result = utilisateurService.modifier(utilisateur);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/utilisateur/{id}", produces = "application/json")
    public ResponseEntity<UtilisateurDTO> deleteUtilisateur(@PathVariable long id) {
        Optional<UtilisateurDTO> utilisateurDTO = utilisateurService.findById(id);
        if (utilisateurDTO.isPresent()) {
            utilisateurService.supprimer(utilisateurDTO.get());
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Méthode permettant à l'utilisateur de valider son panier
     * @param idUtilisateur
     */
    @GetMapping(value = "/utilisateur/validerPanier/{idUtilisateur}", produces = "application/json")
    public void validerPanier(@PathVariable long idUtilisateur) {
        // Récupération de l'utilisateur
        Optional<UtilisateurDTO> utilisateur = utilisateurService.findById(idUtilisateur);
        // Si l'utilisateur existe
        if (utilisateur.isPresent()) {
            // Création du devis non payé et non validé
            Optional<DevisDTO> devisDTO = devisService.genererDevis(utilisateur.get());
            if(devisDTO.isPresent()) {
                // Récupération des éléments du panier
                List<Optional<PanierDTO>> panierList = panierService.findAllByUtilisateur(utilisateur.get());
                // Pour chaque élément du panier, on l'ajoute dans le contenu du devis et on le supprime
                for (Optional<PanierDTO> panierDTO : panierList) {
                    if (panierDTO.isPresent()) {
                        // Création du contenu du devis en BDD
                        contenuDevisService.createFromPanier(devisDTO.get(), panierDTO.get());
                        // Suppression du contenu du panier en BDD
                        panierService.supprimer(panierDTO.get());
                    }
                }
            }
        }
    }

    /**
     * Méthode permettant à l'utilisateur de s'authentifier
     * @return true si la connexion a réussi
     */
    public boolean seConnecter() {
        // URL nécessaire pour l'API
        try {
            URL url = new URL("http://localhost:8081/apiInternes/connexion");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream responseStream = connection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            boolean connexionReussie = mapper.readValue(responseStream, Boolean.class);

            if(connexionReussie)
                System.out.println("Connexion réussie");
            else
                System.out.println("Connexion refusée");
            return connexionReussie;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}