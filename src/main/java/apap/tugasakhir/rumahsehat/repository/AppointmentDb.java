package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, String> {

}
