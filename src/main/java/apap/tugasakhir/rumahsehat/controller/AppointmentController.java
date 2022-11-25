package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.service.AppointmentService;
import apap.tugasakhir.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @GetMapping("/viewall")
    public String listAppointment(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String role = userService.getUserRole();
        List<AppointmentModel> listAppointment = appointmentService.getListAppointment(user);
        model.addAttribute("listAppointment", listAppointment);
        model.addAttribute("role", role);
        return "viewall-appointment";
    }

    @GetMapping(value = "/view/{kode}")
    public String viewAppointment(@PathVariable String kode, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentById(kode);
        String role = userService.getUserRole();
        model.addAttribute("appointment", appointment);
        model.addAttribute("role", role);
        return "view-appointment";
    }

    @GetMapping(value = "/update/{kode}")
    public String updateAppointment(@PathVariable String kode, RedirectAttributes redirectAttrs) {
        AppointmentModel appointment = appointmentService.getAppointmentById(kode);
        String message = appointmentService.updateAppointment(kode);
        redirectAttrs.addFlashAttribute("message", message);
        return "redirect:/appointment/view/{kode}";
    }

}
