package com.mesdevis.apiExternes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiExternes")
public class ApiPaiement {

    /**
     * Méthode permettant de simuler un paiement.
     * Ce paiement a 80% de chance d'être accepté.
     * @return true si le paiement a réussi et false sinon.
     */
    @GetMapping(value = "/paiement", produces = "application/json")
    public boolean payer() {
        int nombreAleatoire = (int) (Math.random() * 100 + 1);
        return nombreAleatoire <= 80;
    }
}
