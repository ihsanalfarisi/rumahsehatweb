package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasienServiceImpl implements PasienService {
    @Autowired
    private PasienDb pasienDb;

    @Override
    public List<PasienModel> getAllPasien() {
        return pasienDb.findAll();
    }

    @Override
    public PasienModel addPasien(PasienModel pasien) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPass = encoder.encode(pasien.getPassword());
        pasien.setPassword(hashedPass);
        return pasienDb.save(pasien);
    }
}
