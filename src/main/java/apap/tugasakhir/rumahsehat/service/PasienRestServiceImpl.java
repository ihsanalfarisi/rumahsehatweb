package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.model.TagihanModel;
import apap.tugasakhir.rumahsehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PasienRestServiceImpl implements PasienRestService{

    @Autowired
    private PasienDb pasienDb;

    @Override
    public PasienModel getPasienByUsername(String username){
        return pasienDb.findByUsernameUsingQuery(username);
    }

    @Override
    public PasienModel updatePasien(PasienModel pasien){
        return pasienDb.save(pasien);
    }
    @Override
    public void paidTagihan(PasienModel pasien, TagihanModel tagihan) {
        pasien.setSaldo(pasien.getSaldo() - tagihan.getJumlahTagihan());
        pasienDb.save(pasien);
    }
}
