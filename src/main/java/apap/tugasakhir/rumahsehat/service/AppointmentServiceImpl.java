package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.UserModel;
import apap.tugasakhir.rumahsehat.repository.AdminDb;
import apap.tugasakhir.rumahsehat.repository.ApotekerDb;
import apap.tugasakhir.rumahsehat.repository.DokterDb;
import org.springframework.security.core.userdetails.User;
import apap.tugasakhir.rumahsehat.repository.AppointmentDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    AppointmentDb appointmentDb;

    @Autowired
    AdminDb adminDb;

    @Autowired
    ApotekerDb apotekerDb;

    @Autowired
    DokterDb dokterDb;

//    @Autowired
//    PasienDb pasienDb;

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
        } else if (user.getRole().equals("pasien")) {
            List<AppointmentModel> appointmentPasien = new ArrayList<AppointmentModel>();
            for (AppointmentModel appointment : result) {
                if (appointment.getPasien().getUuid().equals(user.getUuid())) {
                    appointmentPasien.add(appointment);
                }
            }
            result = appointmentPasien;
        }
        return result;
    }

    @Override
    public UserModel findUserByUsername(User user) {
        String username = user.getUsername();
        String role = user.getAuthorities().toString();
        role = role.substring(1,role.length()-1);
        if (role.equals("admin")) {
            return adminDb.findByUsername(username);
        } else if (role.equals("dokter")) {
            return dokterDb.findByUsername(username);
//        } else if (role.equals("pasien")) {
//            return pasienDb.findByUsername(username);
        } else if (role.equals("apoteker")) {
            return apotekerDb.findByUsername(username);
        }
        return null;
    }
}
