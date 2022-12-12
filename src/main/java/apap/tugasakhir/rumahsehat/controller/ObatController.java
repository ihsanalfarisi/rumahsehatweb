package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.service.ObatService;
import apap.tugasakhir.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/obat")
@Controller
public class ObatController {

    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String listObat(Model model){
        List<ObatModel> listObat = obatService.getListObat();
        String role = userService.getUserRole();
        model.addAttribute("role", role);
        model.addAttribute("listObat", listObat);
        return "viewall-obat";
    }

    @GetMapping("/update/{idObat}")
    public String updateObatForm(@PathVariable String idObat, Model model){
        ObatModel obat = obatService.getObatById(idObat);
        if (obat == null){
            model.addAttribute("idObat", idObat);
            return "error-not-found";
        }

        String role = userService.getUserRole();
        model.addAttribute("role", role);
        model.addAttribute("obat", obat);
        return "form-update-obat";
    }

    @PostMapping("/update/{idObat}")
    public String updateObatSubmit(@PathVariable String idObat, @ModelAttribute ObatModel obat, Model model){
        ObatModel updatedObat = obatService.updateObat(obat);
        String role = userService.getUserRole();
        model.addAttribute("role", role);
        model.addAttribute("idObat", idObat);
        return "update-obat";
    }

    @GetMapping("/update/denied")
    public String updateDenied(Model model){
        String role = userService.getUserRole();
        model.addAttribute("role", role);
        return "update-denied";
    }
}