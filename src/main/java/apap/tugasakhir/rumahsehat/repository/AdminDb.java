package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDb extends JpaRepository<AdminModel, String> {
    AdminModel findByUsername(String username);
    AdminModel findByUuid(String id);
}