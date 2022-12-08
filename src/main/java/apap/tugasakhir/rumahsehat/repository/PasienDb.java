package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, String> {
    PasienModel findByUsername(String username);
    PasienModel findByUuid(String id);
    PasienModel findByEmail(String email);
    @Query("SELECT p FROM PasienModel p WHERE p.username = :username")
    PasienModel findByUsernameUsingQuery(@Param("username") String username);
}
