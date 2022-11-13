package apap.tugasakhir.rumahsehat.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tagihan")
public class TagihanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill")
    @GenericGenerator(name = "bill", strategy = "apap.tugasakhir.rumahsehat.model.MyGenerator", parameters = {
            @Parameter(name = MyGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = MyGenerator.VALUE_PREFIX_PARAMETER, value = "BILL-"),
            @Parameter(name = MyGenerator.NUMBER_FORMAT_PARAMETER, value = "%d")
    })
    private String kode;

    @NotNull
    @Column(nullable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd' 'HH:mm")
    private LocalDateTime tanggalTerbuat;

    @NotNull
    @Column(nullable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd' 'HH:mm")
    private LocalDateTime tanggalBayar;

    @NotNull
    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;

    @NotNull
    @Column(name = "jumlah_tagihan", nullable = false)
    private Integer jumlahTagihan;

    @OneToOne
    @JoinColumn(name = "uuid_pasien", referencedColumnName = "kode", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppointmentModel appointment;
}
