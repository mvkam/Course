import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DateOfCourse {
    @Id
    @GeneratedValue
    private int id;
    @Transient
    private boolean success;
    @Transient
    private long timestamp;
    @Transient
    private boolean historical;
    private String base;
    private String date;
    private String usd;
    private String uah;

    public DateOfCourse() {}

    public void setSuccess (boolean success) {
        this.success = success;
    }
    public boolean getSuccess () {
        return this.success;
    }

    public void setTimestamp (long timestamp) {
        this.timestamp = timestamp;
    }
    public long getTimestamp () {
        return this.timestamp;
    }

    public void setHistorical (boolean historical) {
        this.historical = historical;
    }
    public boolean getHistorical () {
        return this.historical;
    }

    public void setBase (String base) {
        this.base = base;
    }
    public String getBase () {
        return this.base;
    }

    public void setDate (String date) {
        this.date = date;
    }
    public String getDate () {
        return this.date;
    }

    public void setUsd (String usd) { this.usd = usd; }
    public String getUsd () { return this.usd; }

    public void setUAH (String uah) { this.uah = uah; }
    public String getUAH () { return this.uah; }
}
