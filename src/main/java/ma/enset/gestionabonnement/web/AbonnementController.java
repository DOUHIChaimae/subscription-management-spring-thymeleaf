package ma.enset.gestionabonnement.web;

import lombok.AllArgsConstructor;
import ma.enset.gestionabonnement.entities.Abonnement;
import ma.enset.gestionabonnement.services.IAbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Controller
@AllArgsConstructor
public class AbonnementController {
    @Autowired
    private IAbonnementService abonnementService;

    @GetMapping("/user/abonnements/{id}")
    public String afficherAbonnements(@PathVariable Long clientId, Model model) {
        List<Abonnement> abonnements = abonnementService.getAbonnementsByClientId(clientId);
        model.addAttribute("abonnements", abonnements);
        model.addAttribute("id",clientId);
        return "abonnements";
    }
}
