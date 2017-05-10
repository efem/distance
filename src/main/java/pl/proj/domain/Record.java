package pl.proj.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by msz on 08.05.17.
 */
@Entity
@Table(name = "readings")
public class Record {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    private double distance;

    public Date getDate() {
        return date;
    }

    public Record() {
        this(null, 0.0);
    }

    public Record(Date date, double distance) {
        this.date = date;
        this.distance = distance;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getReading() {
        return distance;
    }

    public void setReading(double reading) {
        this.distance = reading;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", reading='" + distance + '\'' +
                '}';
    }
}
