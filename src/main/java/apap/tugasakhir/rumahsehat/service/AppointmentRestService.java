package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;

import java.util.List;

public interface AppointmentRestService {
    AppointmentModel createAppointment(AppointmentModel appointment);

    List<AppointmentModel> retrieveListAppointment();

    List<DokterModel> retrieveListDokter();

    AppointmentModel getAppointmentById(String kode);
}
