package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.UserModel;
import apap.tugasakhir.rumahsehat.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/viewall")
    public String listAppointment(Model model) {
        //cari user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String role = user.getAuthorities().toString();
        role = role.substring(1,role.length()-1);
        List<AppointmentModel> listAppointment = appointmentService.getListAppointment(user);
        model.addAttribute("listAppointment", listAppointment);
        model.addAttribute("role", role);
        return "viewall-appointment";
    }


}
