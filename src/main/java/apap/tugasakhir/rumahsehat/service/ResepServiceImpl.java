package apap.tugasakhir.rumahsehat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugasakhir.rumahsehat.model.ResepModel;
import apap.tugasakhir.rumahsehat.repository.ResepDb;

@Service
public class ResepServiceImpl implements ResepService{
    @Autowired
    ResepDb resepDb;

    @Override
    public void addResep(ResepModel resep) {
        resepDb.save(resep);
    }

    @Override
    public ResepModel getResepById(Long id) {
        Optional<ResepModel> resep = resepDb.findById(id);
        if (resep.isPresent()) {
            return resep.get();
        } else return null;
    }

    @Override
    public List<ResepModel> getListResep(){
        return resepDb.findAll();
    }
}