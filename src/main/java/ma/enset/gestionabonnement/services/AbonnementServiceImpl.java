package ma.enset.gestionabonnement.services;

import jakarta.transaction.Transactional;
import ma.enset.gestionabonnement.entities.Abonnement;
import ma.enset.gestionabonnement.entities.Client;
import ma.enset.gestionabonnement.repositories.AbonnementRepository;
import ma.enset.gestionabonnement.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class AbonnementServiceImpl implements IAbonnementService {
    private ClientRepository clientRepository;
    private AbonnementRepository abonnementRepository;


    public AbonnementServiceImpl(ClientRepository clientRepository, AbonnementRepository abonnementRepository) {
        this.clientRepository = clientRepository;
        this.abonnementRepository = abonnementRepository;

    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }


    @Override
    public Abonnement saveAbonnement(Abonnement abonnement) {
        abonnement.setId(UUID.randomUUID().toString());
        return abonnementRepository.save(abonnement);
    }

}
