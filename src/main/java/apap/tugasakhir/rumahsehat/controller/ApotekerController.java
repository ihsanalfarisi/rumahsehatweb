package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.service.ApotekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apoteker")
public class ApotekerController {
    @Autowired
    private ApotekerService apotekerService;

    @GetMapping(value = "/add")
    private String addUserFormPage(Model model) {
        ApotekerModel apoteker = new ApotekerModel();
        model.addAttribute("apoteker", apoteker);

        return "apoteker/form-add-apoteker";
    }

    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute ApotekerModel apoteker, Model model) {
        apoteker.setRole("apoteker");
        apotekerService.addApoteker(apoteker);
        model.addAttribute("apoteker", apoteker);
        return "redirect:/";
    }
}
