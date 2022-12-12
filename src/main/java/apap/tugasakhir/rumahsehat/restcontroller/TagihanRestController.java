package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.TagihanModel;
import apap.tugasakhir.rumahsehat.repository.TagihanDb;
import apap.tugasakhir.rumahsehat.service.TagihanRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/tagihan")
@CrossOrigin
public class TagihanRestController {

    Logger log = LoggerFactory.getLogger(ResepRestController.class);

    @Autowired
    private TagihanRestService tagihanRestService;

    @Autowired
    private TagihanDb tagihanDb;

    @GetMapping(value = "/list-tagihan")
    private List<TagihanModel> retrieveListTagihan(
            Authentication authentication,
            @RequestParam("username") String username){
        try {
            log.info("Retrieve list tagihan...");
            return tagihanRestService.retrieveListTagihan(username);
        } catch (Exception e){
            log.error("List tagihan not found!");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "List tagihan not found!");
        }
    }

    @PostMapping(value = "/bayar")
    private TagihanModel paidTagihan(Authentication authentication, @RequestParam("kode") String kode) {
        try {
            log.info("Paid tagihan...");
            TagihanModel tagihan = tagihanDb.findTagihanByKode(kode);
            tagihanRestService.paidTagihan(tagihan);
            return tagihan;
        } catch (Exception e) {
            log.error("Tagihan not found!");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tagihan not found!");
        }

    }

}
