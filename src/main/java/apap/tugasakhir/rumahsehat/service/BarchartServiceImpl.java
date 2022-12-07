package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;
import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.repository.JumlahDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BarchartServiceImpl implements BarchartService {
    @Autowired
    private JumlahDb jumlahDb;

    @Override
    public List<Integer> getKuantitasPejualan(List<JumlahObatResepModel> listObat) {
        List<Integer> listData = new ArrayList<Integer>();
        List<JumlahObatResepModel> listJumlahObat = jumlahDb.findAll();
        for (JumlahObatResepModel obat : listObat) {
            String nama = obat.getObat().getNamaObat();
            int total = 0;
            for (JumlahObatResepModel data : listJumlahObat) {
                if (data.getObat().getNamaObat().equals(nama) && data.getResep().getIsDone()) {
                    total+=data.getKuantitas();
                }
            }
            listData.add(total);
        }
        return listData;
    }

    @Override
    public List<Integer> getTotalPendapatan(List<JumlahObatResepModel> listObat) {
        List<Integer> listData = new ArrayList<Integer>();
        List<JumlahObatResepModel> listJumlahObat = jumlahDb.findAll();
        for (JumlahObatResepModel obat : listObat) {
            String nama = obat.getObat().getNamaObat();
            int total = 0;
            for (JumlahObatResepModel data : listJumlahObat) {
                if (data.getObat().getNamaObat().equals(nama) && data.getResep().getIsDone()) {
                    int harga = data.getKuantitas() * data.getObat().getHarga();
                    total+=harga;
                }
            }
            listData.add(total);
        }
        return listData;
    }

    @Override
    public List<String> getString(List<JumlahObatResepModel> listObat) {
        List<String> listNama = new ArrayList<String>();
        for (JumlahObatResepModel obat : listObat) {
            listNama.add(obat.getObat().getNamaObat());
        }
        return listNama;
    }

}
