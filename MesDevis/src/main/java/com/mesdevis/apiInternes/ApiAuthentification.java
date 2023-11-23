package com.mesdevis.apiInternes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiAuthentification {

    /**
     * Méthode permettant de simuler une authentification.
     * Cette authentification a 95% de chance d'être accepté.
     * @return true si l'authentification a réussi et false sinon.
     */
    @GetMapping(value = "/connexion", produces = "application/json")
    public boolean seConnecter() {
        int nombreAleatoire = (int) (Math.random() * 100 + 1);
        return nombreAleatoire <= 95;
    }
}