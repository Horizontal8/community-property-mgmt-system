package cmtyMgmtSys.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//will be ignored if no @Entity
@Table(name = "authorities")
public class Authorities implements Serializable {
    private static final long serialVersionUID = 8734140534986494039L;

    @Id
    private String emailId;

    private String authorities;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
