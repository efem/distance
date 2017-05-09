package pl.proj.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by msz on 08.05.17.
 */
@Entity
@Table(name = "readings")
public class Record {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private LocalDateTime date;

    @NotEmpty
    private double reading;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getReading() {
        return reading;
    }

    public void setReading(double reading) {
        this.reading = reading;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", reading='" + reading + '\'' +
                '}';
    }
}
