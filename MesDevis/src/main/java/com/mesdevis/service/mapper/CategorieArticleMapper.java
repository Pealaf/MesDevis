package com.mesdevis.service.mapper;

import com.mesdevis.entity.CategorieArticle;
import com.mesdevis.entity.dto.CategorieArticleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CategorieArticleMapper {

    private static final Logger log = LoggerFactory.getLogger(CategorieArticleMapper.class);

    public CategorieArticleDTO toDto (CategorieArticle categorieArticle) {
        if (categorieArticle == null) { return null; }
        CategorieArticleDTO categorieArticleDTO = new CategorieArticleDTO();

        categorieArticleDTO.setId(categorieArticle.getId());
        categorieArticleDTO.setIntitule(categorieArticle.getIntitule());
        categorieArticleDTO.setCommercial(categorieArticle.getCommercial());

        return categorieArticleDTO;
    }

    public CategorieArticle toEntity (CategorieArticleDTO categorieArticleDTO) {
        if (categorieArticleDTO == null) {
            return null;
        }
        CategorieArticle categorieArticle = new CategorieArticle(categorieArticleDTO.getId(), categorieArticleDTO.getIntitule(), categorieArticleDTO.getCommercial());

        return categorieArticle;
    }
}
