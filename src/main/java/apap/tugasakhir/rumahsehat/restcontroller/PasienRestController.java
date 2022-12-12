package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.model.TagihanModel;
import apap.tugasakhir.rumahsehat.repository.PasienDb;
import apap.tugasakhir.rumahsehat.repository.TagihanDb;
import apap.tugasakhir.rumahsehat.service.PasienRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/v1/pasien")
public class PasienRestController {

    Logger log = LoggerFactory.getLogger(ResepRestController.class);

    @Autowired
    private PasienRestService pasienRestService;

    @Autowired
    private TagihanDb tagihanDb;

    // Retrieve
    @GetMapping(value = "/profile")
    private PasienModel retrieveProfile(@RequestParam("username") String username){
        try {
            log.info("Retrieve profile...");
            return pasienRestService.getPasienByUsername(username);
        } catch (NoSuchElementException e){
            log.error("Pasien not found!");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pasien dengan username " + username + " tidak ditemukan"
            );
        }
    }

    @PostMapping(value = "/topup")
    private PasienModel topUpSaldo(@RequestParam("username") String username, @RequestBody Map<String,String> data){
        log.info("Top up saldo...");
        PasienModel pasien = pasienRestService.getPasienByUsername(username);
        int saldo_awal = pasien.getSaldo();
        int saldo_topup = Integer.parseInt(data.get("saldo"));
        int saldo_akhir = saldo_awal + saldo_topup;
        pasien.setSaldo(saldo_akhir);
        return pasienRestService.updatePasien(pasien);
    }

    @PostMapping(value = "/bayar")
    private void paidTagihan(Authentication authentication, @RequestParam("kode") String kode) {
        log.info("Paid tagihan...");
        TagihanModel tagihan = tagihanDb.findTagihanByKode(kode);
        System.out.println(tagihan);
        pasienRestService.paidTagihan(tagihan.getAppointment().getPasien(), tagihan);
    }

}
