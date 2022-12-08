package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.PasienModel;

public interface PasienRestService {
    PasienModel getPasienByUsername(String username);
    PasienModel updatePasien(PasienModel pasien);
}
