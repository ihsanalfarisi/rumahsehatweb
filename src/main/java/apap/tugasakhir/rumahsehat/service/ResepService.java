package apap.tugasakhir.rumahsehat.service;

import java.util.List;

import apap.tugasakhir.rumahsehat.model.ResepModel;


public interface ResepService {
    public void addResep(ResepModel resep);
    ResepModel getResepById(Long id);
    List<ResepModel> getListResep();
}
