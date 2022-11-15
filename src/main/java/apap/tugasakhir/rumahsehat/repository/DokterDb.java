package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DokterDb extends JpaRepository<DokterModel, Long> {
    DokterModel findByUsername(String username);
    DokterModel findByUuid(String id);
}
