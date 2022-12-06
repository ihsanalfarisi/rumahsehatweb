package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;

import java.util.List;

public interface BarchartService {
    List<Integer> getKuantitasPejualan(List<JumlahObatResepModel> listNama);

    List<Integer> getTotalPendapatan(List<JumlahObatResepModel> listNama);

    List<String> getString(List<JumlahObatResepModel> listObat);
}
