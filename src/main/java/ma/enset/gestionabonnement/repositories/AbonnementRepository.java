package ma.enset.gestionabonnement.repositories;
import ma.enset.gestionabonnement.entities.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbonnementRepository extends JpaRepository<Abonnement, String> {
     List<Abonnement> findByClient_Id(Long id);
}
