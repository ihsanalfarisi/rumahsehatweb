package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;
import apap.tugasakhir.rumahsehat.model.ResepModel;
import apap.tugasakhir.rumahsehat.repository.ResepDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apap.tugasakhir.rumahsehat.service.ResepRestService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ResepRestServiceImpl implements ResepRestService {
    @Autowired
    private ResepDb resepDb;

    @Override
    public ResepModel getResepById(Long id) {
        Optional<ResepModel> resep = resepDb.findById(id);
        if (resep.isPresent()) {
            return resep.get();
        } else
            throw new NoSuchElementException();
    }

    @Override
    public List<JumlahObatResepModel> getAllJumlahObatResepById(Long id) {
        Optional<ResepModel> resep = resepDb.findById(id);
        if (resep.isPresent()) {
            List<JumlahObatResepModel> listJumlahObatResep = resep.get().getListJumlahObatResep();
            return listJumlahObatResep;
        } else
            throw new NoSuchElementException();

    }
}
