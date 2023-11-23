package com.mesdevis.repository;

import com.mesdevis.entity.DirecteurCommercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
DirecteurCommercialRepository extends JpaRepository<DirecteurCommercial, Long> {
}