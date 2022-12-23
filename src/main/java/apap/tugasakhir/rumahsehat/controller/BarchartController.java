package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;
import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.model.ResepModel;
import apap.tugasakhir.rumahsehat.service.BarchartService;
import apap.tugasakhir.rumahsehat.service.ObatService;
import apap.tugasakhir.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chart/bar")
public class BarchartController {
    @Autowired
    private ObatService obatService;

    @Autowired
    private BarchartService barchartService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public String homeChart( Model model) {
        ResepModel resep = new ResepModel();
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        model.addAttribute("role", userService.getUserRole());
        return "barchart-home";
    }

    @PostMapping(value = "", params = { "save" })
    public String addDataSubmit(@RequestParam (value = "tipe") String tipe, @ModelAttribute ResepModel resep, RedirectAttributes redirectAttrs, Model model) {
        List<JumlahObatResepModel> listObat = resep.getListJumlahObatResep();
        List<String> listNama = barchartService.getString(listObat);
        redirectAttrs.addFlashAttribute("listNama", listNama);

        if(tipe.equals("penjualan")) {
            List<Integer> listData = barchartService.getKuantitasPejualan(listObat);
            redirectAttrs.addFlashAttribute("listData", listData);
            redirectAttrs.addFlashAttribute("role", userService.getUserRole());
            return "redirect:/chart/bar/kuantitas-penjualan";
        } else {
            List<Integer> listData = barchartService.getTotalPendapatan(listObat);
            redirectAttrs.addFlashAttribute("listData", listData);
            redirectAttrs.addFlashAttribute("role", userService.getUserRole());
            return "redirect:/chart/bar/total-pendapatan";
        }
    }

    @PostMapping(value = "", params = { "addRowObat" })
    private String addRowObat(@ModelAttribute ResepModel resep, BindingResult bindingResult, Model model) {
        if (resep.getListJumlahObatResep() == null || resep.getListJumlahObatResep().size() == 0) {
            resep.setListJumlahObatResep(new ArrayList<>());
        }
        if (resep.getListJumlahObatResep().size()<8) {
            resep.getListJumlahObatResep().add(new JumlahObatResepModel());
        } else {
            String message = "Jumlah obat yang dipilih sudah mencapai nilai maksimal";
            model.addAttribute("message", message);
        }
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        model.addAttribute("role", userService.getUserRole());
        return "barchart-home";
    }

    @PostMapping(value = "", params = { "deleteRowObat" })
    private String deleteRowObat(@ModelAttribute ResepModel resep,
                                 @RequestParam("deleteRowObat") Integer row, Model model) {
        if (resep.getListJumlahObatResep() == null || resep.getListJumlahObatResep().size() == 0) {
            resep.setListJumlahObatResep(new ArrayList<>());
        }

        resep.getListJumlahObatResep().remove(row.intValue());
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        model.addAttribute("role", userService.getUserRole());
        return "barchart-home";
    }

    @GetMapping("/kuantitas-penjualan")
    public String viewKuantitas(){
        return "barchart-kuantitas-penjualan";
    }

    @GetMapping("/total-pendapatan")
    public String viewPendapatan(){
        return "barchart-total-pendapatan";
    }
}
