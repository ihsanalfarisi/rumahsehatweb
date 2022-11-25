package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.TagihanModel;
import apap.tugasakhir.rumahsehat.model.UserModel;
import apap.tugasakhir.rumahsehat.repository.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    AppointmentDb appointmentDb;

    @Autowired
    TagihanDb tagihanDb;

    @Autowired
    AdminDb adminDb;

    @Autowired
    ApotekerDb apotekerDb;

    @Autowired
    DokterDb dokterDb;

    @Autowired
    private UserService userService;


    @Override
    public void addAppointment(AppointmentModel appointment) {
        appointmentDb.save(appointment);
    }

    @Override
    public List<AppointmentModel> getListAppointment(User userLoggedIn) {
        UserModel user = findUserByUsername(userLoggedIn);
        List<AppointmentModel> result = appointmentDb.findAll();
        if(user.getRole().equals("dokter")) {
            List<AppointmentModel> appointmentDokter = new ArrayList<AppointmentModel>();
            for (AppointmentModel appointment : result) {
                if (appointment.getDokter().getUuid().equals(user.getUuid())) {
                    appointmentDokter.add(appointment);
                }
            }
            result = appointmentDokter;
        }
        return result;
    }

    @Override
    public AppointmentModel getAppointmentById(String kode) {
        Optional<AppointmentModel> appointment = appointmentDb.findById(kode);
        if(appointment.isPresent()) {
            return appointment.get();
        } else return null;
    }

    @Override
    public String updateAppointment(String kode) {
        AppointmentModel appointment = getAppointmentById(kode);
        if(appointment.getResep() == null || (appointment.getResep().getIsDone() == true && appointment.getResep().getApoteker() != null)) {
            appointment.setIsDone(true);
            addTagihan(kode);
            return "Status appointment berhasil diupdate";
        } else {
            return "Status appointment tidak dapat diupdate karena ada resep yang belum selesai atau belum dikonfirmasi";
        }
    }

    public void addTagihan(String kode) {
        AppointmentModel appointment = getAppointmentById(kode);
        TagihanModel tagihan = new TagihanModel();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = now.format(formatter);
        LocalDateTime tanggalTerbuat = LocalDateTime.parse(formatDateTime, formatter);

        tagihan.setTanggalTerbuat(tanggalTerbuat);
        tagihan.setIsPaid(false);
        tagihan.setJumlahTagihan(appointment.getDokter().getTarif());
        tagihan.setAppointment(appointment);

        tagihanDb.save(tagihan);
    }

    @Override
    public UserModel findUserByUsername(User user) {
        String username = user.getUsername();
        String role = userService.getUserRole();
        if (role.equals("admin")) {
            return adminDb.findByUsername(username);
        } else if (role.equals("dokter")) {
            return dokterDb.findByUsername(username);
        }
        return null;
    }
}
