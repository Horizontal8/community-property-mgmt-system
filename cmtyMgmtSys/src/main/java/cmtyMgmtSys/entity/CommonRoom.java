package cmtyMgmtSys.entity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "commonroom")
public class CommonRoom implements Serializable{
    private static final long serialVersionUID = 2652327633296064143L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CommonRoomType type;

    @OneToMany(mappedBy = "cRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoomBooking> bookings;

    //APIs
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public CommonRoomType getType() {return type;}

    public void setType(CommonRoomType type) {this.type = type;}

    public List<RoomBooking> getBookings() {return bookings;}

    public void setBookings(List<RoomBooking> bookings) {this.bookings = bookings;}
}
