package apap.tugasakhir.rumahsehat.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class AppointmentModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apt")
    @GenericGenerator(name = "apt", strategy = "apap.tugasakhir.rumahsehat.model.MyGenerator", parameters = {
            @Parameter(name = MyGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = MyGenerator.VALUE_PREFIX_PARAMETER, value = "APT-"),
            @Parameter(name = MyGenerator.NUMBER_FORMAT_PARAMETER, value = "%d")
    })
    private String kode;

    @NotNull
    @Column(nullable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd' 'HH:mm")
    private LocalDateTime waktuAwal;

    @NotNull
    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "uuid_pasien", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PasienModel pasien;

    @ManyToOne
    @JoinColumn(name = "uuid_dokter", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DokterModel dokter;

    @OneToOne(mappedBy = "appointment", optional=true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ResepModel resep;

}
