package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.*;
import apap.tugasakhir.rumahsehat.repository.JumlahDb;
import apap.tugasakhir.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class LinechartServiceImpl implements LinechartService {
    @Autowired
    private TagihanDb tagihanDb;

    @Override
    public List<Long> getPendapatanBulanan(ObatModel obat, String strBulan) {
        List<TagihanModel> listTagihan = tagihanDb.findTagihanByObat(obat.getIdObat());
        String strFirstDate = strBulan + "-01";
        LocalDate firstDate = LocalDate.parse(strFirstDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate lastDate = firstDate.withDayOfMonth(firstDate.getMonth().length(firstDate.isLeapYear()));
        List<Long> listIncomePerDay = new ArrayList<>(31);
        for (int i = 0; i < lastDate.getDayOfMonth(); i++) {
            listIncomePerDay.add(0L);
        }
        for (TagihanModel tagihan : listTagihan) {
            // Memeriksa apakah tagihan sudah terbayar atau belum
            if (tagihan.getIsPaid()) {
                // Memeriksa apakah tagihan berada di rentang periode pilihan
                if (((tagihan.getTanggalBayar().toLocalDate().isAfter(firstDate)
                        && tagihan.getTanggalBayar().toLocalDate().isBefore(lastDate)
                        || tagihan.getTanggalBayar().toLocalDate().equals(firstDate))
                        || tagihan.getTanggalBayar().toLocalDate().equals(lastDate))) {
                    AppointmentModel appointment = tagihan.getAppointment();
                    ResepModel resep = appointment.getResep();
                    List<JumlahObatResepModel> listJumlahObatResep = resep.getListJumlahObatResep();
                    int dayOfMonthIdx = tagihan.getTanggalBayar().getDayOfMonth() - 1;
                    // Mencari obat yang dituju di dalam resep
                    for (JumlahObatResepModel jmlObatResep : listJumlahObatResep) {
                        if (jmlObatResep.getObat().getIdObat().equals(obat.getIdObat())) {
                            if (listIncomePerDay.get(dayOfMonthIdx) == null) {
                                listIncomePerDay.add(dayOfMonthIdx,(long) jmlObatResep.getObat().getHarga() * jmlObatResep.getKuantitas());
                            } else {
                                listIncomePerDay.set(dayOfMonthIdx, listIncomePerDay.get(dayOfMonthIdx) + (long) jmlObatResep.getObat().getHarga() * jmlObatResep.getKuantitas());
                            }
                            break;
                        }
                    }
                }
            }
        }
        return listIncomePerDay;
    }

    @Override
    public List<Long> getPendapatanTahunan(ObatModel obat, int tahun) {
        List<TagihanModel> listTagihan = tagihanDb.findTagihanByObat(obat.getIdObat());
        LocalDate firstDate = LocalDate.of(tahun, 1, 1);
        LocalDate lastDate = LocalDate.of(tahun, 12, 31);
        List<Long> listIncomePerMonth = new ArrayList<>(12);
        for (int i = 0; i < lastDate.getMonthValue(); i++) {
            listIncomePerMonth.add(0L);
        }
        for (TagihanModel tagihan : listTagihan) {
            // Memeriksa apakah tagihan sudah terbayar atau belum
            if (tagihan.getIsPaid()) {
                // Memeriksa apakah tagihan berada di rentang periode pilihan
                if (((tagihan.getTanggalBayar().toLocalDate().isAfter(firstDate)
                        && tagihan.getTanggalBayar().toLocalDate().isBefore(lastDate)
                        || tagihan.getTanggalBayar().toLocalDate().equals(firstDate))
                        || tagihan.getTanggalBayar().toLocalDate().equals(lastDate))) {
                    AppointmentModel appointment = tagihan.getAppointment();
                    ResepModel resep = appointment.getResep();
                    List<JumlahObatResepModel> listJumlahObatResep = resep.getListJumlahObatResep();
                    int monthOfYearIdx = tagihan.getTanggalBayar().getMonthValue() - 1;
                    // Mencari obat yang dituju di dalam resep
                    for (JumlahObatResepModel jmlObatResep : listJumlahObatResep) {
                        if (jmlObatResep.getObat().getIdObat().equals(obat.getIdObat())) {
                            if (listIncomePerMonth.get(monthOfYearIdx) == null) {
                                listIncomePerMonth.add(monthOfYearIdx,(long) jmlObatResep.getObat().getHarga() * jmlObatResep.getKuantitas());
                            } else {
                                listIncomePerMonth.set(monthOfYearIdx, listIncomePerMonth.get(monthOfYearIdx) + (long) jmlObatResep.getObat().getHarga() * jmlObatResep.getKuantitas());
                            }
                            break;
                        }
                    }
                }
            }
        }

        return listIncomePerMonth;
    }
    @Override
    public List<String> getListNamaObat(List<JumlahObatResepModel> listObat) {
        List<String> listNama = new ArrayList<>();
        for (JumlahObatResepModel obat : listObat) {
            listNama.add(obat.getObat().getNamaObat());
        }
        return listNama;
    }

    @Override
    public List<String> getAllNamaByListObat(List<ObatModel> listObat) {
        List<String> listNama = new ArrayList<>();
        for (ObatModel obat : listObat) {
            listNama.add(obat.getNamaObat());
        }
        return listNama;
    }
}
