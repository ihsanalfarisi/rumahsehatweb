package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.JumlahObatResepModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JumlahDb extends JpaRepository<JumlahObatResepModel, Long> {
    @Query("SELECT distinct j FROM JumlahObatResepModel as j, ObatModel as o WHERE j.obat = o.idObat")
    List<JumlahObatResepModel> findAllByQuery();
}
