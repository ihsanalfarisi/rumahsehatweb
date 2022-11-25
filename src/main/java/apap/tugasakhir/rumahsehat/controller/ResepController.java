package apap.tugasakhir.rumahsehat.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;
import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.model.ResepModel;
import apap.tugasakhir.rumahsehat.service.AppointmentService;
import apap.tugasakhir.rumahsehat.service.ObatService;
import apap.tugasakhir.rumahsehat.service.ResepService;
import apap.tugasakhir.rumahsehat.service.UserService;

@Controller
@RequestMapping("/resep")
public class ResepController {
    @Autowired
    private ResepService resepService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ObatService obatService;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/add/{kode}")
    public String addResep(@PathVariable String kode, Model model) {
        ResepModel resep = new ResepModel();
        AppointmentModel app = appointmentService.getAppointmentById(kode);
        List<ObatModel> listObat = obatService.getListObat();
        resep.setAppointment(app);
        app.setResep(resep);
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        return "/form-add-resep";
    }

    @PostMapping(value = "/add/{kode}", params = { "save" })
    public String addResepSubmit(@PathVariable String kode, @ModelAttribute ResepModel resep, Model model) {
        if(resep.getListJumlahObatResep() == null) {
            resep.setListJumlahObatResep(new ArrayList<>());
        }
        for (JumlahObatResepModel tmp: resep.getListJumlahObatResep()) {
            tmp.setResep(resep);
        }
        resep.setCreatedAt(LocalDateTime.now());
        AppointmentModel app = appointmentService.getAppointmentById(kode);
        resep.setAppointment(app);
        resepService.addResep(resep);
        model.addAttribute("resep", resep);
        return "/form-add-resep";
    }

    @PostMapping(value = "/add", params = { "addRowObat" })
    private String addRowObat(@ModelAttribute ResepModel resep, BindingResult bindingResult, Model model) {
        if (resep.getListJumlahObatResep() == null || resep.getListJumlahObatResep().size() == 0) {
            resep.setListJumlahObatResep(new ArrayList<>());
        }
        resep.getListJumlahObatResep().add(new JumlahObatResepModel());
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        return "/form-add-resep";
    }

    @PostMapping(value = "/add", params = { "deleteRowObat" })
    private String deleteRowObat(@ModelAttribute ResepModel resep,
            @RequestParam("deleteRowObat") Integer row, Model model) {
        if (resep.getListJumlahObatResep() == null || resep.getListJumlahObatResep().size() == 0) {
            resep.setListJumlahObatResep(new ArrayList<>());
        }
        final Integer rowId = Integer.valueOf(row);
        resep.getListJumlahObatResep().remove(rowId.intValue());
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        return "/form-add-resep";
    }

    @GetMapping("/view/{id}")
    public String viewDetailResep(@PathVariable Long id, Model model, Principal principal) {
        ResepModel resep = resepService.getResepById(id);
        String role = userService.getUserRole();
        model.addAttribute("resep", resep);
        model.addAttribute("listJumlah", resep.getListJumlahObatResep());
        model.addAttribute("role", role);
        return "/view-resep";
    }

    @GetMapping("")
    public String viewAllResep(Model model) {
        List<ResepModel> listResep = resepService.getListResep();
        String role = userService.getUserRole();
        model.addAttribute("role", role);
        model.addAttribute("listResep", listResep);
        return "/viewall-resep";
    }

}