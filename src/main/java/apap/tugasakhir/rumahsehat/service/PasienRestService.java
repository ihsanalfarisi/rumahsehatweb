package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.model.TagihanModel;

public interface PasienRestService {
    PasienModel getPasienByUsername(String username);
    PasienModel updatePasien(PasienModel pasien);
    void paidTagihan(PasienModel pasien, TagihanModel tagihan);
}
