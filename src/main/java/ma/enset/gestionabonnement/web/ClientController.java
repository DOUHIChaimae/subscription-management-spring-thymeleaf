package ma.enset.gestionabonnement.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.gestionabonnement.entities.Client;
import ma.enset.gestionabonnement.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class ClientController {
    private ClientRepository clientRepository;

    @GetMapping(path = "/user/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Client> patientsPage = clientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listClients", patientsPage.getContent());
        model.addAttribute("pages", new int[patientsPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "clients";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword, int page) {
        clientRepository.deleteById(id);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping("/user/clients")
    @ResponseBody
    public List<Client> clientList() {
        return clientRepository.findAll();
    }

    @GetMapping("/admin/formClient")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String formPatient(Model model) {
        model.addAttribute("client", new Client());
        return "formClient";
    }

    @PostMapping("/admin/save")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String savePatient(Model model, @Valid Client client, BindingResult bindingResult,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors()) return "formClient";
        clientRepository.save(client);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/admin/editClient")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatient(Model model, Long id, String keyword, int page) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            throw new RuntimeException("client not found");
        }
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editClient";
    }
}
