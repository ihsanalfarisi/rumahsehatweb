package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.DokterModel;

public interface DokterService {
    public DokterModel addDokter(DokterModel dokter);
    public String encrypt(String password);
}
