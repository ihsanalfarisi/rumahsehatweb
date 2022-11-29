package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.service.AppointmentRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentRestController {

    @Autowired
    private  AppointmentRestService appointmentRestService;

    @PostMapping(value = "/add")
    private AppointmentModel createAppointment(@Valid @RequestBody AppointmentModel appointment, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            return appointmentRestService.createAppointment(appointment);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/list-dokter")
    private List<DokterModel> retrieveListDokter(){
        return appointmentRestService.retrieveListDokter();
    }

    @GetMapping(value = "/list-appointment")
    private List<AppointmentModel> retrieveListAppointment(
            Authentication authentication,
            @RequestParam("username") String username){
        return appointmentRestService.retrieveListAppointment(username);
    }

    @GetMapping(value = "/appointment/{kode}")
    private AppointmentModel retrieveAppointment(
            Authentication authentication,
            @PathVariable("kode") String kode){
        try {
            return appointmentRestService.getAppointmentById(kode);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Code Course " + kode + " not found"
            );
        }
    }
}