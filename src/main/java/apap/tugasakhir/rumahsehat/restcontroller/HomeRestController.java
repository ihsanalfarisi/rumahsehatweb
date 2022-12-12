package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.HomeDataModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.service.PasienRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class HomeRestController {

    Logger log = LoggerFactory.getLogger(HomeRestController.class);

    @Autowired
    PasienRestService pasienRestService;

    @GetMapping("")
    public ResponseEntity home() {
        log.info("Welcome to home!");
        return ResponseEntity.ok("Welcome to home");
    }

    @GetMapping("/home-data")
    public HomeDataModel homeData(@RequestParam("username") String username) {
        try {
            log.info("Retrieve pasien...");
            PasienModel pasien = pasienRestService.getPasienByUsername(username);
            int appointmentCount = pasien.getAppointment() == null ? 0 : pasien.getAppointment().size();
            long saldo = pasien.getSaldo();
            HomeDataModel homeData = new HomeDataModel(saldo, appointmentCount);
            return homeData;
        } catch (NoSuchElementException e) {
            log.error("Pasien not found!");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pasien dengan username " + username + " tidak ditemukan!"
            );
        }
    }
}
