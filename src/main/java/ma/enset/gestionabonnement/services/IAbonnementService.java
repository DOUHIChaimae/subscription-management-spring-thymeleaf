package ma.enset.gestionabonnement.services;

import ma.enset.gestionabonnement.entities.Abonnement;
import ma.enset.gestionabonnement.entities.Client;

import java.util.List;

public interface IAbonnementService {
     Client saveClient(Client client);

     Abonnement saveAbonnement(Abonnement abonnement);
     List<Abonnement> getAbonnementsByClientId(Long clientId);
}
