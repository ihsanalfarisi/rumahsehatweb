package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ObatModel;

import java.util.List;

public interface ObatService {
    List<ObatModel> getListObat();

    List<ObatModel> getListObatByTahun(int year);

    ObatModel getObatById(String idObat);

    ObatModel updateObat(ObatModel obat);

}
