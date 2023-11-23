package com.mesdevis.service.mapper;

import com.mesdevis.entity.Article;
import com.mesdevis.entity.dto.ArticleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ArticleMapper {

    private static final Logger log = LoggerFactory.getLogger(ArticleMapper.class);

    public ArticleDTO toDto (Article article) {
        if (article == null) { return null; }
        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setId(article.getId());
        articleDTO.setLibelle(article.getLibelle());
        articleDTO.setPrixHT(article.getPrixHT());
        articleDTO.setCategorie(article.getCategorie());

        return articleDTO;
    }

    public Article toEntity (ArticleDTO articleDTO) {
        if (articleDTO == null) {
            return null;
        }
        Article article = new Article(articleDTO.getId(), articleDTO.getLibelle(), articleDTO.getPrixHT(), articleDTO.getCategorie());
        return article;
    }
}