package com.mesdevis.service.impl;

import com.mesdevis.entity.CategorieArticle;
import com.mesdevis.entity.dto.CategorieArticleDTO;
import com.mesdevis.repository.CategorieArticleRepository;
import com.mesdevis.service.CategorieArticleService;
import com.mesdevis.service.mapper.CategorieArticleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategorieArticleServiceImpl implements CategorieArticleService {

    private static final Logger log = LoggerFactory.getLogger(CategorieArticleServiceImpl.class);

    @Autowired
    private CategorieArticleRepository categorieArticleRepository;

    @Autowired
    private CategorieArticleMapper categorieArticleMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Optional<CategorieArticleDTO>> findAll() {
        log.info("find all CategorieArticle");
        List<Optional<CategorieArticleDTO>> resultList = new ArrayList<>();
        List<CategorieArticle> categorieArticleList = categorieArticleRepository.findAll();
        for (CategorieArticle categorieArticle : categorieArticleList) {
            if (categorieArticle != null) {
                resultList.add(Optional.of(categorieArticleMapper.toDto(categorieArticle)));
            }
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategorieArticleDTO> findById(long id) {
        log.info("find CategorieArticle by Id");
        try {
            Optional<CategorieArticle> categorieArticle = categorieArticleRepository.findById(id);
            if (categorieArticle.isPresent()) {
                return Optional.of(categorieArticleMapper.toDto(categorieArticle.get()));
            }
        } catch (Exception e) {
            log.info("CategorieArticle with id {} not found", id);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<CategorieArticleDTO> ajouter(CategorieArticleDTO categorieArticleDTO) {
        CategorieArticle categorieArticle = categorieArticleRepository.saveAndFlush(categorieArticleMapper.toEntity(categorieArticleDTO));
        return Optional.of(categorieArticleMapper.toDto(categorieArticle));
    }

    @Override
    @Transactional
    public Optional<CategorieArticleDTO> modifier(CategorieArticleDTO categorieArticleDTO) {
        CategorieArticle categorieArticle = categorieArticleRepository.saveAndFlush(categorieArticleMapper.toEntity(categorieArticleDTO));
        return Optional.of(categorieArticleMapper.toDto(categorieArticle));
    }

    @Override
    @Transactional
    public void supprimer(CategorieArticleDTO categorieArticleDTO) {
        categorieArticleRepository.deleteById(categorieArticleDTO.getId());
    }
}