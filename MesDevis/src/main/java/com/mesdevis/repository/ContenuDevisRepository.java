package com.mesdevis.repository;

import com.mesdevis.entity.ContenuDevis;
import com.mesdevis.entity.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContenuDevisRepository extends JpaRepository<ContenuDevis, Long> {
    Optional<List<ContenuDevis>> findAllByDevis(Devis devis);
}
