package ma.enset.gestionabonnement.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.gestionabonnement.entities.Abonnement;
import ma.enset.gestionabonnement.repositories.AbonnementRepository;
import ma.enset.gestionabonnement.services.IAbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
@Controller
@AllArgsConstructor
public class AbonnementController {
    @Autowired
    private IAbonnementService abonnementService;
    @Autowired
    private AbonnementRepository abonnementRepository;

    @GetMapping("/user/abonnements/{clientId}")
    public String abonnements(@PathVariable Long clientId, Model model) {
        List<Abonnement> abonnements = abonnementService.getAbonnementsByClientId(clientId);
        model.addAttribute("clientId",clientId);
        model.addAttribute("abonnements", abonnements);
        return "abonnements";
    }
    @GetMapping("/admin/deleteAbonnement")
    public String delete(String id) {
        abonnementRepository.deleteById(id);
        return "redirect:/user/index";
    }
    @GetMapping("/admin/editAbonnement")
    public String editAbonnement(Model model, String id) {
        Abonnement abonnement = abonnementRepository.findById(id).orElse(null);
        if (abonnement == null) {
            throw new RuntimeException("abonnement not found");
        }
        model.addAttribute("abonnement", abonnement);
        return "editAbonnement";
    }
    @GetMapping("/admin/formAbonnement")
    public String formAbonnement(Model model) {
        model.addAttribute("abonnement", new Abonnement());
        return "formAbonnement";
    }
    @PostMapping("/admin/saveAbonnement")
    public String saveAbonnement(Model model, @Valid Abonnement abonnement, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "formAbonnement";
        abonnementRepository.save(abonnement
        );
        return "redirect:/user/index";
    }
}
