package ma.enset.gestionabonnement;

import ma.enset.gestionabonnement.services.BootstrapDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionAbonnementApplication {
    @Autowired
    private BootstrapDataService bootstrapDataService;


    public static void main(String[] args) {
        SpringApplication.run(GestionAbonnementApplication.class, args);
    }

    @Bean
    CommandLineRunner start() {
        return args -> {
            this.bootstrapDataService.initClients();
            this.bootstrapDataService.initAbonnements();
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
