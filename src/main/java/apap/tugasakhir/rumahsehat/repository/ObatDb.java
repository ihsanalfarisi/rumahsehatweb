package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObatDb extends JpaRepository<ObatModel, String> {
}
