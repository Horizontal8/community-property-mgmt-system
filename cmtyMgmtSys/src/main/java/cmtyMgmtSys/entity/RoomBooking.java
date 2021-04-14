package cmtyMgmtSys.entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "roombooking")
public class RoomBooking implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date startTime;
    private Date endTime;
    private Date timeCrated;

    @ManyToOne
    @JoinColumn(name = "CRoom_ID", nullable = false)
    private CommonRoom cRoom;

    @ManyToOne
    @JoinColumn(name = "resident_ID", nullable = false)
    private Resident booker;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getTimeCrated() {
        return timeCrated;
    }

    public void setTimeCrated(Date timeCrated) {
        this.timeCrated = timeCrated;
    }

    public CommonRoom getCommonRoom() {
        return cRoom;
    }

    public void setCommonRoom(CommonRoom cRoom) {
        this.cRoom = cRoom;
    }

    public Resident getBooker() {
        return booker;
    }

    public void setBooker(Resident booker) {
        this.booker = booker;
    }
}
