package pl.proj.domain;

import javax.persistence.Entity;

/**
 * Created by msz on 08.05.17.
 */
@Entity
public class Record {

    private Long id;

    private String date;

    private String reading;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
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
