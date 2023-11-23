package com.mesdevis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mesdevis.entity.dto.PanierDTO;
import com.mesdevis.entity.dto.UtilisateurDTO;
import com.mesdevis.service.ArticleService;
import com.mesdevis.service.PanierService;
import com.mesdevis.service.UtilisateurService;
import com.mesdevis.service.mapper.ArticleMapper;
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
public class PanierController {

    private static final Logger log = LoggerFactory.getLogger(PanierController.class);

    @Autowired
    private PanierService panierService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping(value = "/paniers", produces = "application/json")
    public ResponseEntity<List<PanierDTO>> getPaniers() {
        List<PanierDTO> resultList = new ArrayList<>();
        List<Optional<PanierDTO>> panierList = panierService.findAll();
        for (Optional<PanierDTO> panierDTO : panierList) {
            if (panierDTO.isPresent()) {
                resultList.add(panierDTO.get());
            }
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping(value = "/panier/{id}", produces = "application/json")
    public ResponseEntity<PanierDTO> getPanier(@PathVariable long id) {
        Optional<PanierDTO> panier = panierService.findById(id);
        if (panier.isPresent()) {
            return ResponseEntity.ok(panier.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/panier", produces = "application/json")
    public ResponseEntity<PanierDTO> createPanier(@RequestBody PanierDTO panier) {
        if (panier == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<PanierDTO> result = panierService.ajouter(panier);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/panier", produces = "application/json")
    public ResponseEntity<PanierDTO> updatePanier(@RequestBody PanierDTO panier) {
        if (panier == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<PanierDTO> result = panierService.modifier(panier);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/panier/{id}", produces = "application/json")
    public ResponseEntity<PanierDTO> deletePanier(@PathVariable long id) {
        Optional<PanierDTO> panierDTO = panierService.findById(id);
        if (panierDTO.isPresent()) {
            panierService.supprimer(panierDTO.get());
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Méthode permettant d'ajouter des articles au panier
     * @param panier
     * @return
     */
    @PostMapping(value = "/panier/ajouterArticles", produces = "application/json")
    public ResponseEntity<PanierDTO> ajouterArticlesPanier(@RequestBody PanierDTO panier) {
        if (panier == null) {
            return ResponseEntity.noContent().build();
        }

        float montantInitial = panier.getArticle().getPrixHT();
        float montantAuRabais = rechercherMeilleurPrix(montantInitial);
        if(montantInitial != montantAuRabais) {
            // Mise à jour du montant en BDD
            panier.getArticle().setPrixHT(montantAuRabais);
            articleService.modifier(articleMapper.toDto(panier.getArticle()));
        }
        Optional<PanierDTO> result = panierService.ajouter(panier);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Méthode permettant de visualiser le contenu d'un panier d'un utilisateur
     * @param idUtilisateur : id de l'utilisateur où l'on souhaite voir le panier
     * @return le contenu du panier
     */
    @GetMapping(value = "/panier/visualiserPanier/{idUtilisateur}", produces = "application/json")
    public ResponseEntity<List<PanierDTO>> visualiserPanier(@PathVariable long idUtilisateur) {
        // Déclaration et initialisation de la liste des éléments du panier
        List<PanierDTO> resultList = new ArrayList<>();
        // Récupération de l'utilisateur
        Optional<UtilisateurDTO> utilisateur = utilisateurService.findById(idUtilisateur);
        // Récupération des éléments du panier
        if (utilisateur.isPresent()) {
            List<Optional<PanierDTO>> panierList = panierService.findAllByUtilisateur(utilisateur.get());
            for (Optional<PanierDTO> panierDTO : panierList) {
                panierDTO.ifPresent(resultList::add);
            }
        }
        // On retourne la liste des éléments du panier
        return ResponseEntity.ok(resultList);
    }

    /**
     * Méthode permettant de faire appel à une API afin de rechercher un éventuel pris plus intéressant pour un article
     * @param montant
     * @return
     */
    public float rechercherMeilleurPrix(float montant) {
        // URL nécessaire pour l'API
        try {
            URL url = new URL("http://localhost:8081/apiExternes/rechercheMeilleurPrix");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream responseStream = connection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            int remise = mapper.readValue(responseStream, Integer.class);

            // On applique la remise, on arrondit le montant à deux chiffres après la virgule et on retourne la valeur calculée
            float prixFinal =  (float) Math.round((montant - montant * remise / 100) * 100) / 100;

            System.out.println("Meilleur prix : "+prixFinal+"€");
            return prixFinal;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}