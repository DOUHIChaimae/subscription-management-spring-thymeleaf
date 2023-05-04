package ma.enset.gestionabonnement.web;

import jakarta.validation.Valid;
import ma.enset.gestionabonnement.entities.Abonnement;
import ma.enset.gestionabonnement.entities.Client;
import ma.enset.gestionabonnement.exceptions.ResourceNotFoundException;
import ma.enset.gestionabonnement.repositories.AbonnementRepository;
import ma.enset.gestionabonnement.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/clients")
public class ClientRestController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AbonnementRepository abonnementRepository;

    // Endpoint pour récupérer tous les clients
    @GetMapping("")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Endpoint pour récupérer un client par ID
    @GetMapping("/{idClient}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "idClient") Long clientId)
            throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(clientId));
        return ResponseEntity.ok().body(client);
    }

    // Endpoint pour créer un client
    @PostMapping("")
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    // Endpoint pour mettre à jour un client
    @PutMapping("/{idClient}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "idClient") Long clientId,
                                               @Valid @RequestBody Client clientDetails) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(clientId));

        client.setNom(clientDetails.getNom());
        client.setEmail(clientDetails.getEmail());
        client.setUsername(clientDetails.getUsername());

        final Client updatedClient = clientRepository.save(client);
        return ResponseEntity.ok(updatedClient);
    }

    // Endpoint pour supprimer un client
    @DeleteMapping("/{idClient}")
    public ResponseEntity<String> deleteClient(@PathVariable(value = "idClient") Long clientId) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(clientId));

        clientRepository.delete(client);
        return ResponseEntity.ok("Client supprimé avec succès.");
    }

    // Endpoint pour récupérer tous les abonnements
    @GetMapping("/{idClient}/abonnements")
    public List<Abonnement> getAllAbonnements() {
        return abonnementRepository.findAll();
    }

    // Endpoint pour récupérer un abonnement par ID
    //TODO re implemting this method
    @GetMapping("/{idClient}/abonnements/{idAbonnement}")
    public ResponseEntity<Abonnement> getAbonnementById(@PathVariable(value = "idAbonnement") String abonnementId)
            throws ResourceNotFoundException {
        Abonnement abonnement = abonnementRepository.findById(abonnementId)
                .orElseThrow(() -> new ResourceNotFoundException((abonnementId)));
        return ResponseEntity.ok().body(abonnement);
    }

    // Endpoint pour créer un abonnement pour un client donné
    @PostMapping("/{idClient}/abonnements/{idAbonnement}")
    public Abonnement createAbonnement(@PathVariable(value = "clientId") Long clientId,
                                       @Valid @RequestBody Abonnement abonnement) throws ResourceNotFoundException {
        return clientRepository.findById(clientId).map(client -> {
            abonnement.setClient(client);
            return abonnementRepository.save(abonnement);
        }).orElseThrow(() -> new ResourceNotFoundException(clientId));
    }

    // Endpoint pour mettre à jour un abonnement
    @PutMapping("/{idClient}/abonnements/{idAbonnement}")
    public ResponseEntity<Abonnement> updateAbonnement(@PathVariable(value = "idAbonnement") String abonnementId,
                                                       @Valid @RequestBody Abonnement abonnementDetails) throws ResourceNotFoundException {
        Abonnement abonnement = abonnementRepository.findById(abonnementId)
                .orElseThrow(() -> new ResourceNotFoundException(abonnementId));
        abonnement.setDateAbonnement(abonnementDetails.getDateAbonnement());
        abonnement.setType(abonnementDetails.getType());
        abonnement.setSolde(abonnementDetails.getSolde());
        abonnement.setMontant(abonnementDetails.getMontant());

        final Abonnement updatedAbonnement = abonnementRepository.save(abonnement);
        return ResponseEntity.ok(updatedAbonnement);
    }

    // Endpoint pour supprimer un abonnement
    @DeleteMapping("/{idClient}/abonnements/{idAbonnement}")
    public Map<String, Boolean> deleteAbonnement(@PathVariable(value = "id") String abonnementId)
            throws ResourceNotFoundException {
        Abonnement abonnement = abonnementRepository.findById(abonnementId)
                .orElseThrow(() -> new ResourceNotFoundException(abonnementId));

        abonnementRepository.delete(abonnement);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
