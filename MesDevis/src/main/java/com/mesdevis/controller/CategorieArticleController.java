package com.mesdevis.controller;

import com.mesdevis.entity.dto.CategorieArticleDTO;
import com.mesdevis.service.CategorieArticleService;
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
public class CategorieArticleController {

    private static final Logger log = LoggerFactory.getLogger(CategorieArticleController.class);

    @Autowired
    private CategorieArticleService categorieArticleService;

    @GetMapping(value = "/categorieArticles", produces = "application/json")
    public ResponseEntity<List<CategorieArticleDTO>> getCategorieArticles() {
        List<CategorieArticleDTO> resultList = new ArrayList<>();
        List<Optional<CategorieArticleDTO>> categorieArticleList = categorieArticleService.findAll();
        for (Optional<CategorieArticleDTO> categorieArticleDTO : categorieArticleList) {
            if (categorieArticleDTO.isPresent()) {
                resultList.add(categorieArticleDTO.get());
            }
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping(value = "/categorieArticle/{id}", produces = "application/json")
    public ResponseEntity<CategorieArticleDTO> getCategorieArticle(@PathVariable long id) {
        Optional<CategorieArticleDTO> categorieArticle = categorieArticleService.findById(id);
        if (categorieArticle.isPresent()) {
            return ResponseEntity.ok(categorieArticle.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/categorieArticle", produces = "application/json")
    public ResponseEntity<CategorieArticleDTO> createCategorieArticle(@RequestBody CategorieArticleDTO categorieArticle) {
        if (categorieArticle == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<CategorieArticleDTO> result = categorieArticleService.ajouter(categorieArticle);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/categorieArticle", produces = "application/json")
    public ResponseEntity<CategorieArticleDTO> updateCategorieArticle(@RequestBody CategorieArticleDTO categorieArticle) {
        if (categorieArticle == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<CategorieArticleDTO> result = categorieArticleService.modifier(categorieArticle);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/categorieArticle/{id}", produces = "application/json")
    public ResponseEntity<CategorieArticleDTO> deleteCategorieArticle(@PathVariable long id) {
        Optional<CategorieArticleDTO> categorieArticleDTO = categorieArticleService.findById(id);
        if (categorieArticleDTO.isPresent()) {
            categorieArticleService.supprimer(categorieArticleDTO.get());
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}