package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.repository.AppointmentDb;
import apap.tugasakhir.rumahsehat.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class AppointmentRestServiceImpl implements AppointmentRestService {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentDb appointmentDb;

    @Autowired
    private DokterDb dokterDb;

    @Override
    public AppointmentModel createAppointment(AppointmentModel appointment) {
        return appointmentDb.save(appointment);
    }

    @Override
    public List<AppointmentModel> retrieveListAppointment() {
        return appointmentDb.findAll();
    }

    @Override
    public List<DokterModel> retrieveListDokter() { return dokterDb.findAll(); }

    @Override
    public AppointmentModel getAppointmentById(String kode) {
        Optional<AppointmentModel> appointment = appointmentDb.findById(kode);
        if(appointment.isPresent()) {
            return appointment.get();
        } else throw new NoSuchElementException();
    }
}
