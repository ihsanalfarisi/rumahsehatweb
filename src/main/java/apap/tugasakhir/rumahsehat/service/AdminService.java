package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AdminModel;

public interface AdminService {
    AdminModel addAdmin(AdminModel user);

    public String encrypt(String password);

    AdminModel getUserByUsername(String username);
}