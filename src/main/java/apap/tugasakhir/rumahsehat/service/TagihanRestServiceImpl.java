package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.TagihanModel;
import apap.tugasakhir.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagihanRestServiceImpl implements TagihanRestService {

    @Autowired
    private TagihanDb tagihanDb;

    @Autowired
    private PasienService pasienService;

    @Override
    public List<TagihanModel> retrieveListTagihan(String username) {
        List<TagihanModel> temp = tagihanDb.findAll();
        List<TagihanModel> listTagihan = new ArrayList<>();
        for (TagihanModel tagihan : temp) {
            if (tagihan.getAppointment().getPasien().equals(pasienService.getPasienByUsername(username)))
                listTagihan.add(tagihan);
        }
        System.out.println(listTagihan);
        return listTagihan;
    }

    @Override
    public void paidTagihan(TagihanModel tagihan) {
        tagihan.setIsPaid(true);
        tagihan.setTanggalBayar(LocalDateTime.now());
        tagihanDb.save(tagihan);
    }

}
