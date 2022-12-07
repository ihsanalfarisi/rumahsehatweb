package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, String> {
    @Query("SELECT a FROM TagihanModel a WHERE a.appointment = :kode")
    TagihanModel findTagihanByAppointment(@Param("kode") String kode);
}
