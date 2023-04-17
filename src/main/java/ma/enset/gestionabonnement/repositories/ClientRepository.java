package ma.enset.gestionabonnement.repositories;

import ma.enset.gestionabonnement.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByName(String name);

//    @Query("select c from Client c where c.nom like :x and c. < :y")
//    List<Client> searchPatients(@Param("x") String nom, @Param("y") int scoreMin);
}
