package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;

import java.util.List;

public interface AppointmentRestService {
    AppointmentModel createAppointment(AppointmentModel appointment);

    Boolean checkAvailability(AppointmentModel appointment);

    DokterModel getDokter(String uuid);

    PasienModel getPasien(String username);

    List<AppointmentModel> retrieveListAppointment(String username);

    List<DokterModel> retrieveListDokter();

    AppointmentModel getAppointmentById(String kode);
}
