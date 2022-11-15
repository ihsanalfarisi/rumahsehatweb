package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AdminModel;
import apap.tugasakhir.rumahsehat.repository.AdminDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDb adminDb;

    @Override
    public AdminModel addAdmin(AdminModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return adminDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public AdminModel getUserByUsername(String username) {
        return adminDb.findByUsername(username);
    }
}