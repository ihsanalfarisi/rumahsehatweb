package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApotekerDb extends JpaRepository<ApotekerModel, Long> {
    ApotekerModel findByUsername(String username);
    ApotekerModel findByUuid(String id);
}
