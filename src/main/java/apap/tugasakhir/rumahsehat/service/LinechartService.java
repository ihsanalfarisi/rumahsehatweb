package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;
import apap.tugasakhir.rumahsehat.model.ObatModel;

import java.util.List;

public interface LinechartService {

    public List<Long> getPendapatanBulanan(ObatModel obat, String strBulan);

    public List<String> getListNamaObat(List<JumlahObatResepModel> listObat);

    public List<Long> getPendapatanTahunan(ObatModel obat, int tahun);
}
