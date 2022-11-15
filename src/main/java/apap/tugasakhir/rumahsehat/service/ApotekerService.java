package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;

public interface ApotekerService {
    public ApotekerModel addApoteker(ApotekerModel apoteker);
    public String encrypt(String password);
}
