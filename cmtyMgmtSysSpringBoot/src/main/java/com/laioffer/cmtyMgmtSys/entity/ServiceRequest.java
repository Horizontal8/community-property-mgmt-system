package com.laioffer.cmtyMgmtSys.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "servicerequest")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public class ServiceRequest extends Auditable<Long> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private ServiceRequestStatus status;

    @Enumerated(EnumType.STRING)
    private ServiceRequestType type;

    private String description;

    private boolean disabled;

    @Temporal(TemporalType.TIMESTAMP)
    private Date desiredServiceTime;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCompleted;

    @ManyToOne
    @JoinColumn(name = "apt_Id")
    private Apartment apt;
}
