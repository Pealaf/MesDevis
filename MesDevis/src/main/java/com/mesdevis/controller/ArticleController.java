package com.mesdevis.controller;

import com.mesdevis.entity.dto.ArticleDTO;
import com.mesdevis.service.ArticleService;
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
public class ArticleController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/articles", produces = "application/json")
    public ResponseEntity<List<ArticleDTO>> getArticles() {
        List<ArticleDTO> resultList = new ArrayList<>();
        List<Optional<ArticleDTO>> articleList = articleService.findAll();
        for (Optional<ArticleDTO> articleDTO : articleList) {
            if (articleDTO.isPresent()) {
                resultList.add(articleDTO.get());
            }
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping(value = "/article/{id}", produces = "application/json")
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable long id) {
        Optional<ArticleDTO> article = articleService.findById(id);
        if (article.isPresent()) {
            return ResponseEntity.ok(article.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/article", produces = "application/json")
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO article) {
        if (article == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<ArticleDTO> result = articleService.ajouter(article);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/article", produces = "application/json")
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody ArticleDTO article) {
        if (article == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<ArticleDTO> result = articleService.modifier(article);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/article/{id}", produces = "application/json")
    public ResponseEntity<ArticleDTO> deleteArticle(@PathVariable long id) {
        Optional<ArticleDTO> articleDTO = articleService.findById(id);
        if (articleDTO.isPresent()) {
            articleService.supprimer(articleDTO.get());
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
