package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;
import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.service.LinechartService;
import apap.tugasakhir.rumahsehat.service.ObatService;
import apap.tugasakhir.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/chart")
public class ChartController {
    @Autowired
    ObatService obatService;
    @Autowired
    LinechartService linechartService;

    @Autowired
    UserService userService;

    @GetMapping("")
    public String defaultChart(Model model) {
        int currentYear = LocalDateTime.now().getYear();
        List<ObatModel> listObat = obatService.getListObatByTahun(currentYear);
        List<String> listNama = linechartService.getAllNamaByListObat(listObat);
        model.addAttribute("listNama", listNama);
        Map<String, List<Long>> mapDataPendapatanTahunan = new HashMap<>();
        for (ObatModel obat : listObat) {
            List<Long> listDataObat = linechartService.getPendapatanTahunan(obat, currentYear);
            mapDataPendapatanTahunan.put(obat.getNamaObat(), listDataObat);
        }
        model.addAttribute("mapData", mapDataPendapatanTahunan);
        model.addAttribute("tahun", currentYear);
        model.addAttribute("role", userService.getUserRole());
        return "default-linechart";

    }
}
