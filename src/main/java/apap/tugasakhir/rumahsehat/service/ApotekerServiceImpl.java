package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.repository.ApotekerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApotekerServiceImpl implements ApotekerService{
    @Autowired
    private ApotekerDb apotekerDb;

    @Override
    public ApotekerModel addApoteker(ApotekerModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return apotekerDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<ApotekerModel> getAllApoteker() {
        return apotekerDb.findAll();
    }

    @Override
    public ApotekerModel getApotekerByUsername(String nama) {
        return apotekerDb.findByUsername(nama);
    }
}