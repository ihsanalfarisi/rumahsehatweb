package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, String> {
    @Query("SELECT a FROM AppointmentModel a WHERE a.pasien.username = :username")
    List<AppointmentModel> findAllByPasien(@Param("username") String username);

    @Query("SELECT a FROM AppointmentModel a WHERE a.pasien.uuid = :idPasien or a.dokter.uuid = :idDokter")
    List<AppointmentModel> findAllByPasienOrDokter(@Param("idDokter") String idDokter, @Param("idPasien") String idPasien);
}
