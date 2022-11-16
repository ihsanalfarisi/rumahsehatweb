package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.service.PasienService;
import apap.tugasakhir.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pasien")
public class PasienController {
    @Autowired
    private PasienService pasienService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    private String viewAllUser(Model model) {
        List<PasienModel> listPasien = pasienService.getAllPasien();
        String role = userService.getUserRole();
        model.addAttribute("role", role);
        model.addAttribute("listPasien", listPasien);
        return "pasien/view-all-pasien";
    }
}
