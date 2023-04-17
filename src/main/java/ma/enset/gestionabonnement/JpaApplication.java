//package ma.enset.gestionabonnement;
//
//import ma.enset.gestionabonnement.entities.Client;
//import ma.enset.gestionabonnement.repositories.ClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.domain.PageRequest;
//
//import java.util.Date;
//
////public class JpaApplication implements CommandLineRunner {
//    @Autowired
//    private ClientRepository clientRepository;
//    public static void main(String[] args) {
//        SpringApplication.run(JpaApplication.class, args);
//    }
//   // @Bean
////    CommandLineRunner commandLineRunner(ClientRepository clientRepository) {
////        return args -> {
////            Client client = new Client();
////            client.setNom("client1");
////            client.setUsername("user1");
////            client.setEmail("email@gmail.com");
////            clientRepository.save(new Client());
////            clientRepository.findAll().forEach(
////                    c -> System.out.println(client.getNom())
////                    c -> System.out.println(client.getUsername())
////                    c -> System.out.println(client.getEmail())
////
////            );
////        };
//    }
//
//
//}
