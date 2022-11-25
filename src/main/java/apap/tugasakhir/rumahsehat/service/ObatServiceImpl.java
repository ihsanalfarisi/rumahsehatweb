package apap.tugasakhir.rumahsehat.service;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.repository.ObatDb;

@Service
public class ObatServiceImpl implements ObatService{
=======
import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ObatServiceImpl implements ObatService {
>>>>>>> b40b27d009fb36b8b98429828a4469cc2b7d6ff9
    @Autowired
    ObatDb obatDb;

    @Override
<<<<<<< HEAD
    public List<ObatModel> getListObat() {
        return obatDb.findAll();
    }
=======
    public List<ObatModel> getListObat(){
        return obatDb.findAll();
    }

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
>>>>>>> b40b27d009fb36b8b98429828a4469cc2b7d6ff9
}
