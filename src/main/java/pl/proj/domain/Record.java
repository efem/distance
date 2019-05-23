package pl.proj.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "readings")
@Data
public class Record {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    private double distance;

    public Record() {

        this(new Date(), 0.0);
    }

    public Record(double distance) {
        this(new Date(), distance);
    }

    public Record(Date date) {
        this(date, 0.0);
    }

    public Record(Date date, double distance) {
        this.date = date;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Record{" +
                "date='" + date + '\'' +
                ", reading='" + distance + '\'' +
                '}';
    }
}
