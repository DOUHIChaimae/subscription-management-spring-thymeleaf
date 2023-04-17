package ma.enset.gestionabonnement.services;

import ma.enset.gestionabonnement.entities.Abonnement;
import ma.enset.gestionabonnement.entities.Client;

public interface IAbonnementService {
     Client saveClient(Client client);

     Abonnement saveAbonnement(Abonnement abonnement);
}
