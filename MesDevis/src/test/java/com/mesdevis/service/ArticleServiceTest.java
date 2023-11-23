package com.mesdevis.service;

import com.mesdevis.entity.dto.ArticleDTO;
import com.mesdevis.repository.ArticleRepository;
import com.mesdevis.service.mapper.CategorieArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class ArticleServiceTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategorieArticleService categorieArticleService;

    @Autowired
    private CategorieArticleMapper categorieArticleMapper;

   /* @Test
    public void articleService_findAllTest(){
        Optional<CategorieArticleDTO> categorieDTO = categorieArticleService.findById(1);
        // GIVEN
        Article article1 = new Article("ArticleN°1", 120.0f, categorieArticleMapper.toEntity(categorieDTO.get()));
        articleRepository.save(article1);
        Article article2 = new Article("ArticleN°2", 50.1f, categorieArticleMapper.toEntity(categorieDTO.get()));
        articleRepository.save(article2);

        // WHEN
        List<Optional<ArticleDTO>> optionalList = articleService.findAll();
        // THEN
        Assert.notNull(optionalList, "liste lue nulle");
        Assert.notEmpty(optionalList, "liste lue vide");
        Assert.isTrue(optionalList.size() >= 2, "La taille de la liste est au moins de 2 enregistrements");
    }*/

    @Test
    public void articleService_findbyId_1Test(){
        // WHEN
        Optional<ArticleDTO> article = articleService.findById(1);
        // THEN
        Assert.notNull(article, "article 1 lu null");
        Assert.isTrue(article.isPresent(), "L'article 1 est bien lu");
    }

    @Test
    public void articleService_findbyIdMinus1Test(){
        // WHEN
        Optional<ArticleDTO> article = articleService.findById(-1);
        // THEN
        Assert.notNull(article, "article -1 lu nul");
        Assert.isTrue(article.isEmpty(), "L'article -1 est bien vide");
    }
}