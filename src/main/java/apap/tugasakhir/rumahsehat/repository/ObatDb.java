package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObatDb extends JpaRepository<ObatModel, String> {
    @Query("SELECT o FROM TagihanModel t JOIN AppointmentModel a ON t.appointment.kode = a.kode" +
            " JOIN ResepModel r ON a.resep.id = r.id" +
            " JOIN JumlahObatResepModel jor ON jor.resep.id = r.id" +
            " JOIN ObatModel o ON o.idObat = jor.obat.idObat" +
            " WHERE EXTRACT(year from t.tanggalBayar) = :year")
    List<ObatModel> findObatByTahun(@Param("year") Integer year);
}
