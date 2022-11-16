package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.DokterModel;

import java.util.List;

public interface DokterService {
    public DokterModel addDokter(DokterModel dokter);
    public List<DokterModel> getAllDokter();
    public String encrypt(String password);
}
