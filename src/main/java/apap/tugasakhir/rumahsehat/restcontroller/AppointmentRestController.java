package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.service.AppointmentRestService;
import apap.tugasakhir.rumahsehat.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/appointment")
@CrossOrigin
public class AppointmentRestController {

    Logger log = LoggerFactory.getLogger(AppointmentRestController.class);

    @Autowired
    private AppointmentRestService appointmentRestService;
    @Autowired
    private DokterService dokterService;

    @PostMapping(value = "/add")
    private AppointmentModel createAppointment(@Valid @RequestBody Map<String, String> data, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.error("Request body has invalid type or missing field!");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field!"
            );
        } else {
            log.info("Create appointment...");
            AppointmentModel appointment = new AppointmentModel();
            System.out.println(data.get("idDokter"));
            DokterModel dokter = appointmentRestService.getDokter(data.get("idDokter"));
            PasienModel pasien = appointmentRestService.getPasien(data.get("usernamePasien"));
            appointment.setDokter(dokter);
            appointment.setPasien(pasien);
            appointment.setWaktuAwal(LocalDateTime.parse(data.get("waktuAwal")));
            appointment.setIsDone(false);
            if (appointmentRestService.checkAvailability(appointment)) {
                return appointmentRestService.createAppointment(appointment);
            } else {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST
                );
            }

        }
    }

    @GetMapping(value = "/list-dokter")
    private List<DokterModel> retrieveListDokter() {
        try {
            log.info("Retrieve list dokter...");
            return appointmentRestService.retrieveListDokter();
        } catch (Exception e) {
            log.error("List dokter not found!");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "List dokter not found!");
        }
    }

    @GetMapping(value = "/list-appointment")
    private List<AppointmentModel> retrieveListAppointment(
            Authentication authentication,
            @RequestParam("username") String username) {
        try {
            log.info("Retrieve list appointment...");
            return appointmentRestService.retrieveListAppointment(username);
        } catch (Exception e) {
            log.error("List appointment not found!");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "List appointment not found!");
        }
    }

    @GetMapping(value = "/appointment/{kode}")
    private AppointmentModel retrieveAppointment(
            Authentication authentication,
            @PathVariable("kode") String kode) {
        try {
            log.info("Retrieve appointment...");
            return appointmentRestService.getAppointmentById(kode);
        } catch (NoSuchElementException e) {
            log.error("Appointment not found!");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Appointment " + kode + " not found!"
            );
        }
    }
}