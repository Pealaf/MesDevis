package com.mesdevis.service.impl;

import com.mesdevis.entity.Article;
import com.mesdevis.entity.dto.ArticleDTO;
import com.mesdevis.repository.ArticleRepository;
import com.mesdevis.service.ArticleService;
import com.mesdevis.service.mapper.ArticleMapper;
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
public class ArticleServiceImpl implements ArticleService {

    private static final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Optional<ArticleDTO>> findAll() {
        log.info("find all Article");
        List<Optional<ArticleDTO>> resultList = new ArrayList<>();
        List<Article> ArticleList = articleRepository.findAll();
        for (Article article : ArticleList) {
            if (article != null) {
                resultList.add(Optional.of(articleMapper.toDto(article)));
            }
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleDTO> findById(long id) {
        log.info("find Article by Id");
        try {
            Optional<Article> article = articleRepository.findById(id);
            if (article.isPresent()) {
                return Optional.of(articleMapper.toDto(article.get()));
            }
        } catch (Exception e) {
            log.info("Article with id {} not found", id);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<ArticleDTO> ajouter(ArticleDTO articleDTO) {
        Article article = articleRepository.saveAndFlush(articleMapper.toEntity(articleDTO));
        return Optional.of(articleMapper.toDto(article));
    }

    @Override
    @Transactional
    public Optional<ArticleDTO> modifier(ArticleDTO articleDTO) {
        Article article = articleRepository.saveAndFlush(articleMapper.toEntity(articleDTO));
        return Optional.of(articleMapper.toDto(article));
    }

    @Override
    @Transactional
    public void supprimer(ArticleDTO articleDTO) {
        articleRepository.deleteById(articleDTO.getId());
    }
}