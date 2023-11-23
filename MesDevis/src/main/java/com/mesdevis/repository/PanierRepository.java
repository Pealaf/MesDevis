package com.mesdevis.repository;

import com.mesdevis.entity.Panier;
import com.mesdevis.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {

    Optional<List<Panier>> findAllByUtilisateur(Utilisateur utilisateur);

}
