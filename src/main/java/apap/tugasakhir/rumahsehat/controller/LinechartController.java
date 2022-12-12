package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;
import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.model.ResepModel;
import apap.tugasakhir.rumahsehat.service.LinechartService;
import apap.tugasakhir.rumahsehat.service.ObatService;
import apap.tugasakhir.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

@Controller
@RequestMapping("/chart/line")
public class LinechartController {
    @Autowired
    private ObatService obatService;

    @Autowired
    private LinechartService linechartService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String homeChart(Model model) {
        ResepModel resep = new ResepModel();
        List<ObatModel> listObat = obatService.getListObat();
        int currentYear = LocalDateTime.now().getYear();
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        model.addAttribute("maxYear", currentYear);
        model.addAttribute("tahun", currentYear);
        model.addAttribute("role", userService.getUserRole());
        return "linechart-home";
    }

    @PostMapping(value = "", params = { "save" })
    public String addDataSubmit(@RequestParam(value = "tipe") String tipe,
                                @RequestParam(value = "bulan", required = false) String bulan,
                                @RequestParam(value = "tahun", required = false) Long tahun,
                                @ModelAttribute ResepModel resep, RedirectAttributes redirectAttrs,
                                Model model) {
        List<JumlahObatResepModel> listObat = resep.getListJumlahObatResep();
        List<String> listNama = linechartService.getListNamaObat(listObat);
        redirectAttrs.addFlashAttribute("listNama", listNama);
        if(tipe.equals("bulanan")) {
            Map<String, List<Long>> mapDataPendapatanBulanan = new HashMap<>();
            for (JumlahObatResepModel obat : listObat) {
                List<Long> listDataObat = linechartService.getPendapatanBulanan(obat.getObat(), bulan);
                mapDataPendapatanBulanan.put(obat.getObat().getNamaObat(), listDataObat);
            }
            redirectAttrs.addFlashAttribute("mapData", mapDataPendapatanBulanan);
            String namaBulan = Month.of(Integer.parseInt(bulan.substring(5))).getDisplayName(TextStyle.FULL, Locale.getDefault());
            String strTahun = bulan.substring(0, 4);
            redirectAttrs.addFlashAttribute("bulan", namaBulan);
            redirectAttrs.addFlashAttribute("tahun", strTahun);
            redirectAttrs.addFlashAttribute("role", userService.getUserRole());
            return "redirect:/chart/line/pendapatan-bulanan";
        } else {
            Map<String, List<Long>> mapDataPendapatanTahunan = new HashMap<>();
            for (JumlahObatResepModel obat : listObat) {
                List<Long> listDataObat = linechartService.getPendapatanTahunan(obat.getObat(), tahun.intValue());
                mapDataPendapatanTahunan.put(obat.getObat().getNamaObat(), listDataObat);
            }
            redirectAttrs.addFlashAttribute("mapData", mapDataPendapatanTahunan);
            redirectAttrs.addFlashAttribute("tahun", tahun.toString());
            redirectAttrs.addFlashAttribute("role", userService.getUserRole());
            return "redirect:/chart/line/pendapatan-tahunan";
        }
    }

    @PostMapping(value = "", params = { "addRowObat" })
    private String addRowObat(@RequestParam(value = "tipe", required = false) String tipe,
                              @RequestParam(value = "bulan", required = false) String bulan,
                              @RequestParam(value = "tahun", required = false) Long tahun,
                              @ModelAttribute ResepModel resep,
                              BindingResult bindingResult,
                              Model model) {
        if (resep.getListJumlahObatResep() == null || resep.getListJumlahObatResep().size() == 0) {
            resep.setListJumlahObatResep(new ArrayList<>());
        }
        if (resep.getListJumlahObatResep().size()<5) {
            resep.getListJumlahObatResep().add(new JumlahObatResepModel());
        } else {
            String message = "Jumlah obat yang dipilih sudah mencapai nilai maksimal";
            model.addAttribute("message", message);
        }
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        model.addAttribute("tipe", tipe);
        model.addAttribute("bulan", bulan);
        model.addAttribute("tahun", tahun);
        model.addAttribute("role", userService.getUserRole());
        return "linechart-home";
    }

    @PostMapping(value = "", params = { "deleteRowObat" })
    private String deleteRowObat(@RequestParam(value = "tipe", required = false) String tipe,
                                 @RequestParam(value = "bulan", required = false) String bulan,
                                 @RequestParam(value = "tahun", required = false) Long tahun,
                                 @ModelAttribute ResepModel resep,
                                 @RequestParam("deleteRowObat") Integer row, Model model) {
        if (resep.getListJumlahObatResep() == null || resep.getListJumlahObatResep().size() == 0) {
            resep.setListJumlahObatResep(new ArrayList<>());
        }
//        final Integer rowId = Integer.valueOf(row);
//        resep.getListJumlahObatResep().remove(rowId.intValue());
        resep.getListJumlahObatResep().remove(row.intValue());
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        model.addAttribute("tipe", tipe);
        model.addAttribute("bulan", bulan);
        model.addAttribute("tahun", tahun);
        model.addAttribute("role", userService.getUserRole());
        return "linechart-home";
    }

    @GetMapping("/pendapatan-bulanan")
    public String viewKuantitas(){
        return "linechart-pendapatan-bulanan";
    }

    @GetMapping("/pendapatan-tahunan")
    public String viewPendapatan(){
        return "linechart-pendapatan-tahunan";
    }
}
