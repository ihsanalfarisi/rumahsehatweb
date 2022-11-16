package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DokterServiceImpl implements DokterService{
    @Autowired
    private DokterDb dokterDb;

    @Override
    public DokterModel addDokter(DokterModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return dokterDb.save(user);
    }

    @Override
    public List<DokterModel> getAllDokter() {
        return dokterDb.findAll();
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
}

