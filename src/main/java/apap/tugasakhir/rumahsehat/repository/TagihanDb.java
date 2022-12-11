package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, String> {
    @Query("SELECT a FROM TagihanModel a WHERE a.appointment = :kode")
    TagihanModel findTagihanByAppointment(@Param("kode") String kode);

    @Query("SELECT a FROM TagihanModel a WHERE a.kode = :kode")
    TagihanModel findTagihanByKode(@Param("kode") String kode);

    @Query("SELECT t FROM TagihanModel t JOIN AppointmentModel a ON t.appointment.kode = a.kode" +
            " JOIN ResepModel r ON a.resep.id = r.id" +
            " JOIN JumlahObatResepModel jor ON jor.resep.id = r.id" +
            " JOIN ObatModel o ON o.idObat = jor.obat.idObat" +
            " WHERE o.idObat = :idObat")
    List<TagihanModel> findTagihanByObat(@Param("idObat") String idObat);
}
