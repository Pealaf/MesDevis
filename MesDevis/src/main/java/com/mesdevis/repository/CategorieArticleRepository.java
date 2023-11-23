package com.mesdevis.repository;

import com.mesdevis.entity.CategorieArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieArticleRepository extends JpaRepository<CategorieArticle, Long> {
}
