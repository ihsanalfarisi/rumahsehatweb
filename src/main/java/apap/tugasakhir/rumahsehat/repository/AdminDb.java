package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDb extends JpaRepository<AdminModel, Long> {
    AdminModel findByUsername(String username);
    AdminModel findByUuid(String id);
}