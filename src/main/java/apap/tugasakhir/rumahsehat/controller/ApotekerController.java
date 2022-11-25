package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.service.ApotekerService;
import apap.tugasakhir.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/apoteker")
public class ApotekerController {
    @Autowired
    private ApotekerService apotekerService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/add")
    private String addUserFormPage(Model model) {
        ApotekerModel apoteker = new ApotekerModel();
        String role = userService.getUserRole();
        model.addAttribute("role", role);
        model.addAttribute("apoteker", apoteker);
        return "form-add-apoteker";
    }

    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute ApotekerModel apoteker, Model model) {
        apoteker.setRole("apoteker");
        apotekerService.addApoteker(apoteker);
        model.addAttribute("apoteker", apoteker);
        return "redirect:/apoteker";
    }

    @GetMapping(value = "")
    private String viewAllUser(Model model) {
        List<ApotekerModel> listApoteker = apotekerService.getAllApoteker();
        String role = userService.getUserRole();
        model.addAttribute("role", role);
        model.addAttribute("listApoteker", listApoteker);
        return "view-all-apoteker";
    }
}
