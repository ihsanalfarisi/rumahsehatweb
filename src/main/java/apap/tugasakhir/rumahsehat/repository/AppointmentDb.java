package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, String> {
//    @Query("SELECT a FROM AppointmentModel a WHERE a.pasien.uuid = :uuid_pasien")
//    Optional<AppointmentModel> findAppointmentByPasien(@Param("uuid_pasien") String uuid_pasien);
//
//    @Query("SELECT a FROM AppointmentModel a WHERE a.dokter.uuid = :uuid_dokter")
//    Optional<AppointmentModel> findAppointmentByDokter(@Param("uuid_dokter") String uuid_dokter);
}
