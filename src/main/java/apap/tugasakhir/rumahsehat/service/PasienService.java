package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;

import java.util.List;

public interface PasienService {
    public List<PasienModel> getAllPasien();
    public PasienModel addPasien(PasienModel pasien);
    public PasienModel getPasienByUsername(String username);
    public PasienModel getPasienByEmail(String email);
}
