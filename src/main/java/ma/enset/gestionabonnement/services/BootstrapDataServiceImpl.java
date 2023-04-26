package ma.enset.gestionabonnement.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.gestionabonnement.entities.Abonnement;
import ma.enset.gestionabonnement.entities.Client;
import ma.enset.gestionabonnement.entities.Type;
import ma.enset.gestionabonnement.repositories.AbonnementRepository;
import ma.enset.gestionabonnement.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Transactional
public class BootstrapDataServiceImpl implements BootstrapDataService {
    private ClientRepository clientRepository;
    private AbonnementRepository abonnementRepository;
    private IAbonnementService abonnementService;


    @Override
    public void initClients() {
        Stream.of("chaimae", "amina", "nour")
                .forEach(name -> {
                    Client client = new Client();
                    client.setNom(name);
                    client.setEmail(name + "@gmail.com");
                    client.setUsername("User1");
                    abonnementService.saveClient(client);
                });
    }

    @Override
    public void initAbonnements() {
        Client client = clientRepository.findAll().get(0);
        Abonnement abonnement = new Abonnement();
        abonnement.setDateAbonnement(new Date());
        abonnement.setMontant(((int) Math.random()) * 1000);
        abonnement.setSolde(((int) Math.random()) * 15477 + 2001);
        abonnement.setType(Type.INTERNET);
        abonnement.setClient(client);
        abonnementService.saveAbonnement(abonnement);
    }
}
