package com.mesdevis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mesdevis.entity.dto.ClientDTO;
import com.mesdevis.entity.dto.ContenuDevisDTO;
import com.mesdevis.entity.dto.DevisDTO;
import com.mesdevis.service.ClientService;
import com.mesdevis.service.ContenuDevisService;
import com.mesdevis.service.DevisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;
    @Autowired
    private DevisService devisService;
    @Autowired
    private ContenuDevisService contenuDevisService;

    @GetMapping(value = "/clients", produces = "application/json")
    public ResponseEntity<List<ClientDTO>> getClients() {
        List<ClientDTO> resultList = new ArrayList<>();
        List<Optional<ClientDTO>> clientList = clientService.findAll();
        for (Optional<ClientDTO> clientDTO : clientList) {
            if (clientDTO.isPresent()) {
                resultList.add(clientDTO.get());
            }
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping(value = "/client/{id}", produces = "application/json")
    public ResponseEntity<ClientDTO> getClient(@PathVariable long id) {
        Optional<ClientDTO> client = clientService.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/client", produces = "application/json")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO client) {
        if (client == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<ClientDTO> result = clientService.ajouter(client);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/client", produces = "application/json")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO client) {
        if (client == null) {
            return ResponseEntity.noContent().build();
        }
        Optional<ClientDTO> result = clientService.modifier(client);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/client/{id}", produces = "application/json")
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable long id) {
        Optional<ClientDTO> clientDTO = clientService.findById(id);
        if (clientDTO.isPresent()) {
            clientService.supprimer(clientDTO.get());
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Méthode permettant au client de régler un devis
     * @param idClient
     * @param idDevis
     * @return
     */
    @GetMapping(value = "/client/reglerDevis/{idClient}/{idDevis}", produces = "application/json")
    public String reglerDevis(@PathVariable long idClient, @PathVariable long idDevis) {
        // Récupération du client
        Optional<ClientDTO> client = clientService.findById(idClient);
        // Si le client existe bien
        if (client.isPresent()) {
            ClientDTO clientDTO = client.get();
            // Récupération du devis
            Optional<DevisDTO> devis = devisService.findById(idDevis);
            // Si le devis existe bien
            if(devis.isPresent()) {
                DevisDTO devisDTO = devis.get();
                // Si le devis est validé
                if(devisDTO.isValidation()) {
                    // Si le devis n'est pas payé
                    if(!devisDTO.isPaiement()) {
                        // Si le devis est bien celui du client, alors on peut procéder au paiement
                        if (devisDTO.getUtilisateur().equals(clientDTO.getUtilisateur())) {
                            // Règlement du devis
                            if (payer()) {
                                // Si le paiement est accepté
                                devisDTO.setPaiement(true);
                                // Mise à jour du devis en BDD
                                devisService.modifier(devisDTO);

                                // Calcul du montant du devis
                                float montantDevis = calculerMontantDevis(devisDTO);
                                // Ajout de la TVA
                                montantDevis *= 1.2f;
                                // On arrondit le montant à deux chiffres après la virgule
                                montantDevis = (float) Math.round(montantDevis * 100) / 100;
                                return "Le devis a été réglé avec succès pour un montant de " + montantDevis + "€ !";
                            } else {
                                return "Le paiement a été refusé.";
                            }
                        }
                    } else {
                        return "Le devis a déjà été réglé.";
                    }
                } else {
                    return "Le devis n'a pas encore été validé. Vous ne pouvez donc pas procéder au paiement.";
                }
            }
        }
        return "Une erreur est survenue lors du règlement du devis.";
    }

    /**
     * Méthode permettant de faire appel à une API afin de procéder au paiement
     * @return
     */
    public boolean payer() {
        // URL nécessaire pour l'API
        try {
            URL url = new URL("http://localhost:8081/apiExternes/paiement");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream responseStream = connection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            boolean resultat = mapper.readValue(responseStream, Boolean.class);

            if (resultat) {
                System.out.println("Paiement accepté");
            } else {
                System.out.println("Paiement refusé");
            }
            return resultat;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode permettant de calculer le montant du devis
     * @param devisDTO
     * @return
     */
    public float calculerMontantDevis(DevisDTO devisDTO) {
        float prixTotal = 0;
        List<Optional<ContenuDevisDTO>> contenuDevisList = contenuDevisService.findAllByDevis(devisDTO);
        for (Optional<ContenuDevisDTO> contenuDevisDTO : contenuDevisList) {
            if (contenuDevisDTO.isPresent()) {
                prixTotal += contenuDevisDTO.get().getArticle().getPrixHT() * contenuDevisDTO.get().getQuantiteArticles();
            }
        }
        return prixTotal;
    }
}
