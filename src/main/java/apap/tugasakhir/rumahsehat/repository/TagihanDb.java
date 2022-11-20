package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, String> {
}
