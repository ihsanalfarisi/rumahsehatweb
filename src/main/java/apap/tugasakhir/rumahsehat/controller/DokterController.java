package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dokter")
public class DokterController {
    @Autowired
    private DokterService dokterService;

    @GetMapping(value = "/add")
    private String addUserFormPage(Model model) {
        DokterModel dokter = new DokterModel();
        model.addAttribute("dokter", dokter);

        return "dokter/form-add-dokter";
    }

    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute DokterModel dokter, Model model) {
        dokter.setRole("dokter");
        dokterService.addDokter(dokter);
        model.addAttribute("dokter", dokter);
        return "redirect:/";
    }
}
