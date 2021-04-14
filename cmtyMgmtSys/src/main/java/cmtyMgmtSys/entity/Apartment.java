package cmtyMgmtSys.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "apartment")
public class Apartment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String aptNum;
    private boolean avail;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Resident> residents;

    public int getId() {
        return id;
    }

    public String getAptNum() {
        return aptNum;
    }

    public boolean isAvail() {
        return avail;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAptNum(String aptNum) {
        this.aptNum = aptNum;
    }

    public void setAvail(boolean avail) {
        this.avail = avail;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }
}
