package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;
import apap.tugasakhir.rumahsehat.model.ResepModel;

import java.util.List;

public interface ResepRestService {
    ResepModel getResepById(Long id);
    List<JumlahObatResepModel> getAllJumlahObatResepById(Long id);
}
