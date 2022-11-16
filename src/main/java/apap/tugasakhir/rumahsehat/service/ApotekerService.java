package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;

import java.util.List;

public interface ApotekerService {
    public ApotekerModel addApoteker(ApotekerModel apoteker);
    public List<ApotekerModel> getAllApoteker();
    public String encrypt(String password);
}
