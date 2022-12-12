package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.repository.AppointmentDb;
import apap.tugasakhir.rumahsehat.repository.DokterDb;
import apap.tugasakhir.rumahsehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Autowired
    private PasienDb pasienDb;

    @Override
    public AppointmentModel createAppointment(AppointmentModel appointment) {
        return appointmentDb.save(appointment);
    }

    @Override
    public Boolean checkAvailability(AppointmentModel appointment) {
        boolean status = true;
        List<AppointmentModel> listAppointment = appointmentDb.findAll();
        List<AppointmentModel> listOther = new ArrayList<AppointmentModel>();
        for (AppointmentModel apt : listAppointment) {
            if(apt.getPasien().getUuid().equals(appointment.getPasien().getUuid()) || apt.getDokter().getUuid().equals(appointment.getDokter().getUuid())){
                listOther.add(apt);
            }
        }
        for (AppointmentModel other : listOther) {
            if ((appointment.getWaktuAwal().isAfter(other.getWaktuAwal().minusHours(1)) && appointment.getWaktuAwal().isBefore(other.getWaktuAwal()))
                    || (appointment.getWaktuAwal().isBefore(other.getWaktuAwal().plusHours(1)) && appointment.getWaktuAwal().isAfter(other.getWaktuAwal()))
                    || appointment.getWaktuAwal().equals(other.getWaktuAwal())) {
                status = false;
                break;
            }
        }
        return status;
    }

    @Override
    public DokterModel getDokter(String uuid) {
        return dokterDb.findByUuid(uuid);
    }

    @Override
    public PasienModel getPasien(String username) {
        return pasienDb.findByUsername(username);
    }

    @Override
    public List<AppointmentModel> retrieveListAppointment(String username) {
        return appointmentDb.findAllByPasien(username);
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
