package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.UserModel;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface AppointmentService {
    void addAppointment(AppointmentModel appointment);

    List<AppointmentModel> getListAppointment(User user);

    AppointmentModel getAppointmentById(String kode);

    String updateAppointment(String kode);

    UserModel findUserByUsername(User user);
}
