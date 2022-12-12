package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;
import apap.tugasakhir.rumahsehat.model.ResepModel;
import apap.tugasakhir.rumahsehat.service.ResepRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/resep")
public class ResepRestController {

    Logger log = LoggerFactory.getLogger(ResepRestController.class);

    @Autowired
    ResepRestService resepRestService;

    @GetMapping("/list-jumlah-obat/{idResep}")
    public List<JumlahObatResepModel> getJumlahObatResepByResepId(Authentication authentication,
            @PathVariable("idResep") Long idResep) {
        try {
            log.info("Retrieve list obat from resep...");
            return resepRestService.getAllJumlahObatResepById(idResep);
        } catch (Exception e) {
            log.error("List obat resep from resep not found!");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "List obat resep not found!");
        }
    }
}
