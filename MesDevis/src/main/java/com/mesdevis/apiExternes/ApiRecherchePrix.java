package com.mesdevis.apiExternes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiExternes")
public class ApiRecherchePrix {

    /**
     * Méthode permettant de simuler la recherche du meilleur prix.
     * Pour cela, elle retourne une remise entre 0 et 5 %.
     * @return le prix avec ou sans rabais.
     */
    @GetMapping(value = "/rechercheMeilleurPrix", produces = "application/json")
    public float rechercherMeilleurPrix() {
        return (int) (Math.random() * 6);
    }
}
