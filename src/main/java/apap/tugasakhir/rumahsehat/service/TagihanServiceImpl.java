package apap.tugasakhir.rumahsehat.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;
import apap.tugasakhir.rumahsehat.model.ResepModel;
import apap.tugasakhir.rumahsehat.model.TagihanModel;
import apap.tugasakhir.rumahsehat.repository.TagihanDb;

@Service
@Transactional
public class TagihanServiceImpl implements TagihanService{
    @Autowired
    TagihanDb tagihanDb;

    @Override
    public void addTagihanResep(TagihanModel tagihan, AppointmentModel appointment) {
        appointment.setIsDone(true);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = now.format(formatter);
        LocalDateTime tanggalTerbuat = LocalDateTime.parse(formatDateTime, formatter);
        tagihan.setTanggalTerbuat(tanggalTerbuat);
        tagihan.setIsPaid(false);
        tagihan.setAppointment(appointment);
        tagihan.setJumlahTagihan(appointment.getDokter().getTarif() + getHarga(appointment.getResep()));

        tagihanDb.save(tagihan);
    }
    
    @Override
    public int getHarga(ResepModel resep) {
        int harga = 0;
        int temp;

        for (JumlahObatResepModel jumlah : resep.getListJumlahObatResep()) {
            temp = jumlah.getKuantitas() * jumlah.getObat().getHarga();
            harga += temp;
        }

        return harga;
    }

    public TagihanModel getTagihanByAppointment(AppointmentModel appointment) {
        return tagihanDb.findTagihanByAppointment(appointment.getKode());
    }

}
