package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.TagihanModel;
import apap.tugasakhir.rumahsehat.repository.TagihanDb;
import apap.tugasakhir.rumahsehat.service.TagihanRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/tagihan")
@CrossOrigin
public class TagihanRestController {

    @Autowired
    private TagihanRestService tagihanRestService;

    @Autowired
    private TagihanDb tagihanDb;

    @GetMapping(value = "/list-tagihan")
    private List<TagihanModel> retrieveListTagihan(
            Authentication authentication,
            @RequestParam("username") String username){
        return tagihanRestService.retrieveListTagihan(username);
    }

    @PostMapping(value = "/bayar")
    private TagihanModel paidTagihan(Authentication authentication, @RequestParam("kode") String kode) {
        TagihanModel tagihan = tagihanDb.findTagihanByKode(kode);
        tagihanRestService.paidTagihan(tagihan);
        return tagihan;
    }

}
