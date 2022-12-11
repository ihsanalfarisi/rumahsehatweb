package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.repository.ObatDb;
import apap.tugasakhir.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ObatServiceImpl implements ObatService {
    @Autowired
    ObatDb obatDb;


    @Override
    public List<ObatModel> getListObat(){
        return obatDb.findAll();
    }

    @Override
    public List<ObatModel> getListObatByTahun(int year) {
        return obatDb.findObatByTahun(year);
    };

    @Override
    public ObatModel getObatById(String idObat){
        Optional<ObatModel> obat = obatDb.findById(idObat);
        if (obat.isPresent()){
            return obat.get();
        } else return null;
    }

    @Override
    public ObatModel updateObat(ObatModel obat){
        obatDb.save(obat);
        return obat;
    }
}
