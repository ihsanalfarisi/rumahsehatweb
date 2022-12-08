package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.service.PasienRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/pasien")
public class PasienRestController {
    @Autowired
    private PasienRestService pasienRestService;

    // Retrieve
    @GetMapping(value = "/profile")
    private PasienModel retrieveProfile(@RequestParam("username") String username){
        try {
            return pasienRestService.getPasienByUsername(username);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pasien dengan username " + username + " tidak ditemukan"
            );
        }
    }

    @PostMapping(value = "/topup")
    private PasienModel topUpSaldo(@RequestParam("username") String username, @RequestBody Map<String,String> data){
        PasienModel pasien = pasienRestService.getPasienByUsername(username);
        int saldo_awal = pasien.getSaldo();
        int saldo_topup = Integer.parseInt(data.get("saldo"));
        int saldo_akhir = saldo_awal + saldo_topup;
        pasien.setSaldo(saldo_akhir);
        return pasienRestService.updatePasien(pasien);
    }

}
