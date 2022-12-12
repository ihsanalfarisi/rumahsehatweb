package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;
import apap.tugasakhir.rumahsehat.model.ResepModel;
import apap.tugasakhir.rumahsehat.service.ResepRestService;
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
    @Autowired
    ResepRestService resepRestService;

    @GetMapping(value = "/{id}")
    private ResepModel retrieveResep(
            Authentication authentication,
            @PathVariable("id") Long id) {
        try {
            return resepRestService.getResepById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Resep " + id + " not found");
        }
    }

    @GetMapping("/list-jumlah-obat/{idResep}")
    public List<JumlahObatResepModel> getJumlahObatResepByResepId(Authentication authentication,
            @PathVariable("idResep") Long idResep) {
        return resepRestService.getAllJumlahObatResepById(idResep);
    }
}
