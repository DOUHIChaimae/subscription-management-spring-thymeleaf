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
@RequestMapping("/rest")
public class AbonnementRestController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AbonnementRepository abonnementRepository;

    // Endpoint pour récupérer tous les clients
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Endpoint pour récupérer un client par ID
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId)
            throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(clientId));
        return ResponseEntity.ok().body(client);
    }

    // Endpoint pour créer un client
    @PostMapping("/clients")
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    // Endpoint pour mettre à jour un client
    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long clientId,
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
    @DeleteMapping("/clients/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long clientId)
            throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(clientId));

        clientRepository.delete(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    // Endpoint pour récupérer tous les abonnements
    @GetMapping("/abonnements")
    public List<Abonnement> getAllAbonnements() {
        return abonnementRepository.findAll();
    }

    // Endpoint pour récupérer un abonnement par ID
    @GetMapping("/abonnements/{id}")
    public ResponseEntity<Abonnement> getAbonnementById(@PathVariable(value = "id") String abonnementId)
            throws ResourceNotFoundException {
        Abonnement abonnement = abonnementRepository.findById(abonnementId)
                .orElseThrow(() -> new ResourceNotFoundException((abonnementId)));
        return ResponseEntity.ok().body(abonnement);
    }

    // Endpoint pour créer un abonnement pour un client donné
    @PostMapping("/clients/{clientId}/abonnements")
    public Abonnement createAbonnement(@PathVariable(value = "clientId") Long clientId,
                                       @Valid @RequestBody Abonnement abonnement) throws ResourceNotFoundException {
        return clientRepository.findById(clientId).map(client -> {
            abonnement.setClient(client);
            return abonnementRepository.save(abonnement);
        }).orElseThrow(() -> new ResourceNotFoundException(clientId));
    }

    // Endpoint pour mettre à jour un abonnement
    @PutMapping("/abonnements/{id}")
    public ResponseEntity<Abonnement> updateAbonnement(@PathVariable(value = "id") String abonnementId,
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
    @DeleteMapping("/abonnements/{id}")
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
